package ru.relex.cryptocurrencyexchange.dto.cryptowallet.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties
public class CryptoWalletRequestToSale {

    private UUID secretKey;

    @Pattern(regexp = "[A-Za-z]{3}")
    @NotNull
    private String currencyFrom;
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal amountFrom;

}
