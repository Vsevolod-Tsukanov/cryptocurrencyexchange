package ru.relex.cryptocurrencyexchange.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.cryptocurrencyexchange.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Override
    @EntityGraph(value = "UserWithWallet")
    Optional<User> findById(UUID uuid);

    @EntityGraph(value = "UserWithWallet")
    Optional<User> findByUserName(String userName);

    boolean existsUserByUserNameOrEmail(String username, String email);
}
