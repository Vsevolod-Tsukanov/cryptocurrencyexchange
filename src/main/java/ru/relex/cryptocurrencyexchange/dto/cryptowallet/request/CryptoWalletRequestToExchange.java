package ru.relex.cryptocurrencyexchange.dto.cryptowallet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoWalletRequestToExchange {

    private UUID secretKey;

    @Pattern(regexp = "[A-Za-z]{3}")
    @NotNull
    private String currencyAbbreviation;
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal amountFrom;
}
