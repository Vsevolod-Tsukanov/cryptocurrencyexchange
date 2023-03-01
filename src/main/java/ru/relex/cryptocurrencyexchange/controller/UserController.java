package ru.relex.cryptocurrencyexchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.relex.cryptocurrencyexchange.dto.user.UserRequestWithoutId;
import ru.relex.cryptocurrencyexchange.dto.user.UserResponse;
import ru.relex.cryptocurrencyexchange.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UUID registerUser(@Valid @RequestBody UserRequestWithoutId request) {
        return userService.registerUser(request);
    }

}
