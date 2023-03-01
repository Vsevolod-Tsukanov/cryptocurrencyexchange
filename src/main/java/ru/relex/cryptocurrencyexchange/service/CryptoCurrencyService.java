package ru.relex.cryptocurrencyexchange.service;

import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyRequest;
import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyResponse;

import java.util.List;

public interface CryptoCurrencyService {

    CryptoCurrencyResponse changeCryptoCurrencyValue(CryptoCurrencyRequest request);

    List<CryptoCurrencyResponse> getAllCryptoCurrency();
}
