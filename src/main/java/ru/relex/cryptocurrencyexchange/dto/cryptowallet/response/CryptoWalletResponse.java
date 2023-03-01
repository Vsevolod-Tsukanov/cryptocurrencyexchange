package ru.relex.cryptocurrencyexchange.dto.cryptowallet.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.cryptocurrencyexchange.dto.cryptocount.CryptoCountResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWalletResponse {

    private BigDecimal balance;
    private List<CryptoCountResponse> cryptoCounts;

}
