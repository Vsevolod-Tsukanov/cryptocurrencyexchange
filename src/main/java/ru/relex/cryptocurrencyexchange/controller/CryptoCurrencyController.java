package ru.relex.cryptocurrencyexchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyRequest;
import ru.relex.cryptocurrencyexchange.dto.cryptocurrency.CryptoCurrencyResponse;
import ru.relex.cryptocurrencyexchange.service.CryptoCurrencyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cryptoCurrency")
@RequiredArgsConstructor
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public CryptoCurrencyResponse changeCryptoCurrencyValue(@Valid @RequestBody CryptoCurrencyRequest request) {
        return cryptoCurrencyService.changeCryptoCurrencyValue(request);
    }

    @GetMapping
    public List<CryptoCurrencyResponse> getAllCryptoCurrencies() {
        return cryptoCurrencyService.getAllCryptoCurrency();
    }
}
