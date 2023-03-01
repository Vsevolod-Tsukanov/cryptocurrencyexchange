package ru.relex.cryptocurrencyexchange.dto.cryptocount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCountRequest {
    private String abbreviation;
    private BigDecimal amount;
}
