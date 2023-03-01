package ru.relex.cryptocurrencyexchange.service;

import ru.relex.cryptocurrencyexchange.dto.user.UserRequestWithoutId;
import ru.relex.cryptocurrencyexchange.dto.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponse> getAllUsers();

    UUID registerUser(UserRequestWithoutId request);

}
