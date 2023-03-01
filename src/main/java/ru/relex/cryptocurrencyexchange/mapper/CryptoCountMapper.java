package ru.relex.cryptocurrencyexchange.mapper;

import org.springframework.stereotype.Component;
import ru.relex.cryptocurrencyexchange.dto.cryptocount.CryptoCountResponse;
import ru.relex.cryptocurrencyexchange.model.CryptoCount;

import java.math.BigDecimal;

@Component
public class CryptoCountMapper {

    public CryptoCountResponse toResponse(CryptoCount cryptoCount) {
        BigDecimal countOfCrypto = cryptoCount.getCryptoBalance();
        String abbreviation = cryptoCount.getCryptoCurrency().getAbbreviation();

        return new CryptoCountResponse(abbreviation, countOfCrypto);
    }

}
