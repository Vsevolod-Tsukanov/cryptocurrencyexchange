package ru.relex.cryptocurrencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "crypto_schema", name = "crypto_count")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "CryptoCountWithCurrencyAndWallet", attributeNodes = {
        @NamedAttributeNode(value = "cryptoCurrency"),
        @NamedAttributeNode(value = "cryptoWallet", subgraph = "withWalletOwner")
}, subgraphs = {
        @NamedSubgraph(name = "withWalletOwner", attributeNodes = {@NamedAttributeNode("walletOwner")})
})
public class CryptoCount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private CryptoCurrency cryptoCurrency;

    @Column(name = "count")
    private BigDecimal cryptoBalance;

    @ManyToOne
    @JoinColumn(name = "crypto_wallet_id")
    private CryptoWallet cryptoWallet;
}
