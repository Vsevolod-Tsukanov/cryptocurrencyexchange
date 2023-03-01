package ru.relex.cryptocurrencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.cryptocurrencyexchange.model.CryptoCurrency;

import java.util.Optional;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    Optional<CryptoCurrency> findByAbbreviation(String abbreviation);
}
