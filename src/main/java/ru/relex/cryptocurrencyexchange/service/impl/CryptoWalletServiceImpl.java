package ru.relex.cryptocurrencyexchange.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToReplenish;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToTransferToCard;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponse;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForTransfer;
import ru.relex.cryptocurrencyexchange.exception.CalculatingException;
import ru.relex.cryptocurrencyexchange.exception.NoEntityFoundException;
import ru.relex.cryptocurrencyexchange.logger.OperationLogger;
import ru.relex.cryptocurrencyexchange.mapper.CryptoWalletMapper;
import ru.relex.cryptocurrencyexchange.model.CryptoCount;
import ru.relex.cryptocurrencyexchange.model.CryptoCurrency;
import ru.relex.cryptocurrencyexchange.model.CryptoWallet;
import ru.relex.cryptocurrencyexchange.repository.CryptoCountRepository;
import ru.relex.cryptocurrencyexchange.repository.CryptoCurrencyRepository;
import ru.relex.cryptocurrencyexchange.repository.CryptoWalletRepository;
import ru.relex.cryptocurrencyexchange.service.CryptoWalletService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CryptoWalletServiceImpl implements CryptoWalletService {

    private final CryptoWalletRepository cryptoWalletRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCountRepository cryptoCountRepository;
    private final CryptoWalletMapper cryptoWalletMapper;
    private final OperationLogger operationLogger;

    @Override
    public Optional<CryptoWalletResponse> getBalanceByUserId(UUID id) {
        return cryptoWalletRepository.findByWalletOwnerId(id)
                .map(cryptoWalletMapper::toDefaultResponse);
    }

    @Override
    public BigDecimal getBalanceOfAllUsersInSelectedCurrency(String currencyAbbreviation) {

        BigDecimal totalBalance;

        if (currencyAbbreviation.equalsIgnoreCase("RUB") || currencyAbbreviation.equalsIgnoreCase(" ")) {
            totalBalance = cryptoWalletRepository.findSumOfAllBalances()
                    .orElseThrow(() -> new CalculatingException("Something went wrong while trying calculate balance"));
        } else {
            totalBalance = cryptoWalletRepository.findSumOfCurrencyOfAllBalances(currencyAbbreviation)
                    .orElseThrow(() -> new CalculatingException("CryptoCurrency with this abbreviation was NOT FOUND"));
        }
        return totalBalance;
    }

    @Override
    public CryptoWalletResponseForTransfer transferMoneyToCreditCard(CryptoWalletRequestToTransferToCard request) {

        CryptoWallet entity = cryptoWalletRepository.findByWalletOwnerId(request.getWalletOwnerSecretKey())
                .orElseThrow(() -> new NoEntityFoundException("Crypto Wallet with given ID does not exist"));
        BigDecimal balance = entity.getBalance();
        BigDecimal count = request.getMoney();
        if (isEnoughMoney(entity, count)) {
            balance = balance.subtract(count);
            entity.setBalance(balance);
        } else throw new CalculatingException("Insufficient balance");

        cryptoWalletRepository.save(entity);
        operationLogger.log(entity, "Transfer Money To Credit Card");
        return cryptoWalletMapper.toTransferResponse(entity);
    }

    @Override
    @Transactional
    public CryptoWalletResponse topUpTheBalance(CryptoWalletRequestToReplenish request) {

        CryptoWallet entity = cryptoWalletRepository.findByWalletOwnerId(request.getSecretKey())
                .orElseThrow(() -> new NoEntityFoundException("Crypto Wallet Owner with given ID does not exist"));

        BigDecimal newBalance = entity.getBalance().add(request.getAmountToReplenishTheBalance());
        entity.setBalance(newBalance);
        cryptoWalletRepository.save(entity);

        operationLogger.log(entity, "Top Up The Balance");
        return cryptoWalletMapper.toDefaultResponse(entity);
    }

    public CryptoWalletResponseForExchange buyCryptoCurrency(CryptoWalletRequestToExchange request) {

        CryptoWallet entity = cryptoWalletRepository.findByWalletOwnerId(request.getSecretKey())
                .orElseThrow(() -> new NoEntityFoundException("Crypto Wallet with given Wallet Owner ID does not exist"));
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findByAbbreviation(request.getCurrencyAbbreviation())
                .orElseThrow(() -> new NoEntityFoundException("CryptoCurrency with given abbreviation does not exist"));
        CryptoCount currentCount = cryptoCountRepository.findByCryptoCurrencyAndCryptoWallet(cryptoCurrency, entity)
                .orElse(new CryptoCount());

        BigDecimal balance;
        BigDecimal sumOfCurrency;
        BigDecimal amountToExchange = request.getAmountFrom();
        BigDecimal currencyValue = cryptoCurrency.getValue();

        if (isEnoughMoney(entity, amountToExchange)) {
            balance = entity.getBalance();
            sumOfCurrency = amountToExchange.divide(currencyValue, 10, RoundingMode.FLOOR);
            balance = balance.subtract(amountToExchange);
            entity.setBalance(balance);
            exchangeMoneyToCrypto(entity, currentCount, cryptoCurrency, sumOfCurrency);
        } else throw new CalculatingException("Insufficient balance to exchange currency!");

        operationLogger.log(entity, "Buy Crypto Currency");
        return cryptoWalletMapper.toExchangeResponse(entity, currentCount, request, sumOfCurrency);
    }

    public CryptoWalletResponseForExchange sellCryptoCurrency(CryptoWalletRequestToExchange request) {

        CryptoWallet cryptoWallet = cryptoWalletRepository.findByWalletOwnerId(request.getSecretKey())
                .orElseThrow(() -> new NoEntityFoundException("Crypto Wallet with given ID does not exist"));
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findByAbbreviation(request.getCurrencyAbbreviation())
                .orElseThrow(() -> new NoEntityFoundException("CryptoCurrency with given abbreviation does not exist"));
        CryptoCount cryptoCount = cryptoCountRepository.findByCryptoCurrencyAndCryptoWallet(cryptoCurrency, cryptoWallet)
                .orElse(new CryptoCount());

        BigDecimal balance;
        BigDecimal newCryptoBalance;
        BigDecimal amountToExchange = request.getAmountFrom();
        BigDecimal currencyValue = cryptoCurrency.getValue();

        if (cryptoCount.getCryptoBalance().compareTo(amountToExchange) >= 0) {
            balance = cryptoWallet.getBalance();
            newCryptoBalance = cryptoCount.getCryptoBalance().subtract(amountToExchange);
            balance = balance.add(amountToExchange.multiply(currencyValue));
            cryptoCount.setCryptoBalance(newCryptoBalance);
            cryptoWallet.setBalance(balance);
        } else throw new RuntimeException("Insufficient balance to exchange currency!");

        operationLogger.log(cryptoWallet, "Sell Crypto Currency");
        return cryptoWalletMapper.toExchangeResponse(cryptoWallet, cryptoCount, request, newCryptoBalance);
    }

    private boolean isEnoughMoney(CryptoWallet cryptoWallet, BigDecimal sumToCompare) {
        return cryptoWallet.getBalance().compareTo(sumToCompare) >= 0;
    }

    private void exchangeMoneyToCrypto(CryptoWallet cryptoWallet,
                                       CryptoCount currentCount,
                                       CryptoCurrency cryptoCurrency,
                                       BigDecimal sumOfCurrency
    ) {

        BigDecimal newCryptoBalance;

        if (currentCount.getId() != null) {
            newCryptoBalance = currentCount.getCryptoBalance().add(sumOfCurrency);
            currentCount.setCryptoBalance(newCryptoBalance);

            cryptoCountRepository.save(currentCount);
        } else {
            currentCount.setCryptoCurrency(cryptoCurrency);
            currentCount.setCryptoWallet(cryptoWallet);
            currentCount.setCryptoBalance(sumOfCurrency);

            cryptoCountRepository.save(currentCount);
            cryptoWallet.getCryptoCounts().add(currentCount);
        }
        cryptoWalletRepository.save(cryptoWallet);
    }

}
