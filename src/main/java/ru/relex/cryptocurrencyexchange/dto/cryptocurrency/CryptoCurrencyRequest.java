package ru.relex.cryptocurrencyexchange.dto.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCurrencyRequest {

    @Pattern(regexp = "[A-Z]{3,6}", message = "Неверный формат аббревиатуры")
    @NotBlank
    @NotNull
    private String abbreviation;

    @DecimalMin(value = "0")
    private BigDecimal newCurrencyValue;

}
