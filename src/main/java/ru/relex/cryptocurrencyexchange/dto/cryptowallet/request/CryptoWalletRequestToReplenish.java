package ru.relex.cryptocurrencyexchange.dto.cryptowallet.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoWalletRequestToReplenish {
    private UUID secretKey;
    @DecimalMin(value = "0")
    private BigDecimal amountToReplenishTheBalance;
}
