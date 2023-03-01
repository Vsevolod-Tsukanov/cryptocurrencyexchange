package ru.relex.cryptocurrencyexchange.mapper;

import org.springframework.stereotype.Component;
import ru.relex.cryptocurrencyexchange.dto.user.UserRequestWithoutId;
import ru.relex.cryptocurrencyexchange.dto.user.UserResponse;
import ru.relex.cryptocurrencyexchange.model.User;
import ru.relex.cryptocurrencyexchange.security.entity.Role;

@Component
public class UserMapper {

    public User toNewEntity(UserRequestWithoutId request) {
        User entity = new User();
        entity.setUserName(request.getUserName());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setRole(Role.USER);

        return entity;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUserName(), user.getEmail(), user.getRole().toString());
    }

}
