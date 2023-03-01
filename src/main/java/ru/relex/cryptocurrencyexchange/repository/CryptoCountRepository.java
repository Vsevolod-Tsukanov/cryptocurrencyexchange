package ru.relex.cryptocurrencyexchange.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.cryptocurrencyexchange.model.CryptoCount;
import ru.relex.cryptocurrencyexchange.model.CryptoCurrency;
import ru.relex.cryptocurrencyexchange.model.CryptoWallet;

import java.util.List;
import java.util.Optional;

public interface CryptoCountRepository extends JpaRepository<CryptoCount, Long> {

    @EntityGraph(value = "CryptoCountWithCurrencyAndWallet")
    Optional<CryptoCount> findByCryptoCurrencyAndCryptoWallet(CryptoCurrency cryptoCurrency, CryptoWallet wallet);

    @Override
    @EntityGraph(value = "CryptoCountWithCurrencyAndWallet")
    List<CryptoCount> findAll();

    @Override
    @EntityGraph(value = "CryptoCountWithCurrencyAndWallet")
    Optional<CryptoCount> findById(Long aLong);
}
