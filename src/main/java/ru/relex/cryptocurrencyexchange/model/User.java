package ru.relex.cryptocurrencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ru.relex.cryptocurrencyexchange.security.entity.Role;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "crypto_schema", name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "UserWithWallet", attributeNodes = {
        @NamedAttributeNode("cryptoWallet")
})
public class User {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuidFromIp", strategy = "org.hibernate.id.uuid.CustomVersionOneStrategy")
    @GeneratedValue(generator = "uuid_gen_strategy")
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crypto_wallet_id")
    private CryptoWallet cryptoWallet;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
