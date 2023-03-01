package ru.relex.cryptocurrencyexchange.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.relex.cryptocurrencyexchange.exception.NoEntityFoundException;
import ru.relex.cryptocurrencyexchange.mapper.SecurityUserMapper;
import ru.relex.cryptocurrencyexchange.model.User;
import ru.relex.cryptocurrencyexchange.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final SecurityUserMapper securityUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User entity = userRepository.findByUserName(username)
                .orElseThrow(() -> new NoEntityFoundException("User with given Username does not exist"));

        return securityUserMapper.toSecurityUser(entity);
    }
}
