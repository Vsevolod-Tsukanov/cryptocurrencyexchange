package ru.relex.cryptocurrencyexchange.mapper;

import org.springframework.stereotype.Component;
import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyResponse;
import ru.relex.cryptocurrencyexchange.model.CryptoCurrency;

@Component
public class CryptoCurrencyMapper {

    public CryptoCurrencyResponse toResponse(CryptoCurrency cryptoCurrency) {
        return new CryptoCurrencyResponse(cryptoCurrency.getAbbreviation(), cryptoCurrency.getValue());
    }

}
