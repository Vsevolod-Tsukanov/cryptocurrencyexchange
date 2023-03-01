package ru.relex.cryptocurrencyexchange.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.relex.cryptocurrencyexchange.dto.cryptocount.CryptoCountResponse;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponse;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForTransfer;
import ru.relex.cryptocurrencyexchange.model.CryptoCount;
import ru.relex.cryptocurrencyexchange.model.CryptoWallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CryptoWalletMapper {

    private final CryptoCountMapper cryptoCountMapper;

    public CryptoWalletResponse toDefaultResponse(CryptoWallet cryptoWallet) {

        CryptoWalletResponse response = new CryptoWalletResponse();
        List<CryptoCountResponse> cryptoCounts = cryptoWallet.getCryptoCounts().stream()
                .map(cryptoCountMapper::toResponse)
                .collect(Collectors.toList());
        response.setCryptoCounts(cryptoCounts);
        response.setBalance(cryptoWallet.getBalance());

        return response;
    }

    public CryptoWalletResponseForExchange toExchangeResponse(CryptoWallet cryptoWallet,
                                                              CryptoCount cryptoCount,
                                                              CryptoWalletRequestToExchange request,
                                                              BigDecimal sumOfCurrency) {
        CryptoWalletResponseForExchange response = new CryptoWalletResponseForExchange();
        response.setAmountFrom(request.getAmountFrom());
        response.setCurrencyAbbreviation(request.getCurrencyAbbreviation());
        response.setBalanceAfterOperation(cryptoWallet.getBalance());
        response.setAmountTo(sumOfCurrency);
        response.setCryptoBalanceAfterOperation(cryptoCount.getCryptoBalance());

        return response;
    }


    public CryptoWalletResponseForTransfer toTransferResponse(CryptoWallet wallet) {
        return new CryptoWalletResponseForTransfer(wallet.getBalance());
    }

}
