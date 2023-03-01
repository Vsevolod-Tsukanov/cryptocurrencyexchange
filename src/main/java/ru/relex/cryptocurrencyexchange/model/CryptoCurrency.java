package ru.relex.cryptocurrencyexchange.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "crypto_schema", name = "cryptocurrency")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CryptoCurrency {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "abbreviation")
    private String abbreviation;

    //    Цена
    @Column(name = "value")
    private BigDecimal value;
}
