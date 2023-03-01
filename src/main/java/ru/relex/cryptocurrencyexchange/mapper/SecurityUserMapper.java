package ru.relex.cryptocurrencyexchange.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.relex.cryptocurrencyexchange.model.User;

@Component
public class SecurityUserMapper {

    public UserDetails toSecurityUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getRole().getAuthorities()
        );
    }

}
