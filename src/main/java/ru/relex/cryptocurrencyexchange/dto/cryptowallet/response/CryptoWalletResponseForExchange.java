package ru.relex.cryptocurrencyexchange.dto.cryptowallet.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties
public class CryptoWalletResponseForExchange {
    private String currencyAbbreviation;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private BigDecimal balanceAfterOperation;
    private BigDecimal cryptoBalanceAfterOperation;

}
