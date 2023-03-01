package ru.relex.cryptocurrencyexchange.service;

import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToReplenish;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToTransferToCard;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponse;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForTransfer;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CryptoWalletService {

    Optional<CryptoWalletResponse> getBalanceByUserId(UUID id);

    CryptoWalletResponse topUpTheBalance(CryptoWalletRequestToReplenish request);

    CryptoWalletResponseForExchange buyCryptoCurrency(CryptoWalletRequestToExchange request);

    CryptoWalletResponseForExchange sellCryptoCurrency(CryptoWalletRequestToExchange request);

    BigDecimal getBalanceOfAllUsersInSelectedCurrency(String currencyAbbreviation);

    CryptoWalletResponseForTransfer transferMoneyToCreditCard(CryptoWalletRequestToTransferToCard request);


}
