package ru.relex.cryptocurrencyexchange.dto.cryptocount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCountResponse {
    private String currencyAbbreviation;
    private BigDecimal cryptoBalance;
}
