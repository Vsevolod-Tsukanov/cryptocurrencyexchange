package ru.relex.cryptocurrencyexchange.dto.cryptowallet.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoWalletRequestToTransferToCard {

    private UUID walletOwnerSecretKey;
    @DecimalMin(value = "0")
    private BigDecimal money;
    @Pattern(regexp = "[0-9]{4}[- .]?[0-9]{4}[- .]?[0-9]{4}[- .]?[0-9]{4}")
    private String creditCard;

}
