package ru.relex.cryptocurrencyexchange.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.relex.cryptocurrencyexchange.model.CryptoWallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CryptoWalletRepository extends JpaRepository<CryptoWallet, Long> {
    @Override
    @EntityGraph(value = "WalletWithCryptoCountsAndWalletOwner")
    List<CryptoWallet> findAll();

    @Override
    @EntityGraph(value = "WalletWithCryptoCountsAndWalletOwner")
    Optional<CryptoWallet> findById(Long aLong);

    @EntityGraph(value = "WalletWithCryptoCountsAndWalletOwner")
    Optional<CryptoWallet> findByWalletOwnerId(UUID id);

    @Query(value = "SELECT  sum(w.balance) FROM crypto_schema.crypto_wallet w", nativeQuery = true)
    Optional<BigDecimal> findSumOfAllBalances();

    @Query(value = "SELECT sum(cc.count) FROM crypto_schema.crypto_wallet cw\n" +
            "JOIN crypto_schema.crypto_count cc ON cc.crypto_wallet_id = cw.id\n" +
            "JOIN crypto_schema.cryptocurrency cr\n" +
            "WHERE cr.abbreviation = :currAbbreviation", nativeQuery = true)
    Optional<BigDecimal> findSumOfCurrencyOfAllBalances(@Param("currAbbreviation") String currencyAbbreviation);
}
