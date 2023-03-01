package ru.relex.cryptocurrencyexchange.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyRequest;
import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyResponse;
import ru.relex.cryptocurrencyexchange.exception.NoEntityFoundException;
import ru.relex.cryptocurrencyexchange.mapper.CryptoCurrencyMapper;
import ru.relex.cryptocurrencyexchange.model.CryptoCurrency;
import ru.relex.cryptocurrencyexchange.repository.CryptoCurrencyRepository;
import ru.relex.cryptocurrencyexchange.service.CryptoCurrencyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public CryptoCurrencyResponse changeCryptoCurrencyValue(CryptoCurrencyRequest request) {

        CryptoCurrency entity = cryptoCurrencyRepository.findByAbbreviation(request.getAbbreviation())
                .orElseThrow(() -> new NoEntityFoundException("Crypto Currency with given Abbreviation does not exist"));
        entity.setValue(request.getNewCurrencyValue());
        cryptoCurrencyRepository.save(entity);

        return cryptoCurrencyMapper.toResponse(entity);
    }

    @Override
    public List<CryptoCurrencyResponse> getAllCryptoCurrency() {
        return cryptoCurrencyRepository.findAll().stream()
                .map(cryptoCurrencyMapper::toResponse)
                .collect(Collectors.toList());
    }
}
