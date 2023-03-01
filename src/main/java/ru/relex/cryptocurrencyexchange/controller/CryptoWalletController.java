package ru.relex.cryptocurrencyexchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToReplenish;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.request.CryptoWalletRequestToTransferToCard;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponse;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForExchange;
import ru.relex.cryptocurrencyexchange.dto.cryptowallet.response.CryptoWalletResponseForTransfer;
import ru.relex.cryptocurrencyexchange.service.CryptoWalletService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/cryptoWallet")
@RequiredArgsConstructor
public class CryptoWalletController {

    private final CryptoWalletService cryptoWalletService;

    @GetMapping("balance/{walletOwnerId}")
    public CryptoWalletResponse showBalance(@PathVariable UUID walletOwnerId) {
        return cryptoWalletService.getBalanceByUserId(walletOwnerId)
                .orElseGet(CryptoWalletResponse::new);
    }

    @GetMapping("balance/total/{abbr}")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public BigDecimal getTotalBalance(@Valid @PathVariable String abbr) {
        return cryptoWalletService.getBalanceOfAllUsersInSelectedCurrency(abbr);
    }

    @PostMapping("/balance")
    public CryptoWalletResponse topUpTheBalance(@Valid @RequestBody CryptoWalletRequestToReplenish request) {
        return cryptoWalletService.topUpTheBalance(request);
    }

    @PostMapping("/currency/purchase")
    public CryptoWalletResponseForExchange buyCryptoCurrency(@Valid @RequestBody CryptoWalletRequestToExchange request) {
        return cryptoWalletService.buyCryptoCurrency(request);
    }

    @PostMapping("/currency/sale")
    public CryptoWalletResponseForExchange sellCryptoCurrency(@Valid @RequestBody CryptoWalletRequestToExchange request) {
        return cryptoWalletService.sellCryptoCurrency(request);
    }

    @PostMapping("/transfer")
    public CryptoWalletResponseForTransfer transferMoneyToCard(@Valid @RequestBody CryptoWalletRequestToTransferToCard request) {
        return cryptoWalletService.transferMoneyToCreditCard(request);
    }
}
