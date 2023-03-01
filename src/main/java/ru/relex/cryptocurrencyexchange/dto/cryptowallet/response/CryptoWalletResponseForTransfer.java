package ru.relex.cryptocurrencyexchange.dto.cryptowallet.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CryptoWalletResponseForTransfer {
    private BigDecimal balance;
}
