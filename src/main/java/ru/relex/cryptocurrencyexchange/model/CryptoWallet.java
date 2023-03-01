package ru.relex.cryptocurrencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(schema = "crypto_schema", name = "crypto_wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "WalletWithCryptoCountsAndWalletOwner", attributeNodes = {
        @NamedAttributeNode(value = "cryptoCounts", subgraph = "withCryptoCurrency"),
        @NamedAttributeNode(value = "walletOwner")
}, subgraphs = {
        @NamedSubgraph(name = "withCryptoCurrency", attributeNodes = {@NamedAttributeNode("cryptoCurrency")})
})
public class CryptoWallet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "cryptoWallet", cascade = CascadeType.ALL)
    private List<CryptoCount> cryptoCounts;

    @OneToOne(mappedBy = "cryptoWallet")
    private User walletOwner;
}
