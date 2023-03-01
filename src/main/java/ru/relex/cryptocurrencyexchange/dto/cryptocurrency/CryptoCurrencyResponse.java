package ru.relex.cryptocurrencyexchange.dto.cryptocurrency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CryptoCurrencyResponse {

    private String abbreviation;
    private BigDecimal value;

}
