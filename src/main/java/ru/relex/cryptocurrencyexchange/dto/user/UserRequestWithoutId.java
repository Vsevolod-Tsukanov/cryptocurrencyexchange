package ru.relex.cryptocurrencyexchange.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestWithoutId {
    @NotNull
    @NotBlank
    @Length(max = 24)
    private String userName;
    @NotNull
    @NotBlank
    @Length(max = 24)
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Length(max = 24)
    private String password;
}
