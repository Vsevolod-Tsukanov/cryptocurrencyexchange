package ru.relex.cryptocurrencyexchange.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.relex.cryptocurrencyexchange.dto.user.UserRequestWithoutId;
import ru.relex.cryptocurrencyexchange.dto.user.UserResponse;
import ru.relex.cryptocurrencyexchange.exception.NotUniqueValuesException;
import ru.relex.cryptocurrencyexchange.mapper.UserMapper;
import ru.relex.cryptocurrencyexchange.model.CryptoWallet;
import ru.relex.cryptocurrencyexchange.model.User;
import ru.relex.cryptocurrencyexchange.repository.CryptoWalletRepository;
import ru.relex.cryptocurrencyexchange.repository.UserRepository;
import ru.relex.cryptocurrencyexchange.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CryptoWalletRepository cryptoWalletRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UUID registerUser(UserRequestWithoutId request) {

        User entity;

        if (!userRepository.existsUserByUserNameOrEmail(request.getUserName(), request.getEmail())) {
            entity = userMapper.toNewEntity(request);
            createWalletForUser(entity);
            userRepository.save(entity);
        } else throw new NotUniqueValuesException("User with given userName or Email already exist");

        return entity.getId();
    }


    private void createWalletForUser(User user) {
        CryptoWallet cryptoWallet = new CryptoWallet();
        cryptoWallet.setBalance(BigDecimal.valueOf(0));
        cryptoWalletRepository.save(cryptoWallet);
        user.setCryptoWallet(cryptoWallet);
    }
}
