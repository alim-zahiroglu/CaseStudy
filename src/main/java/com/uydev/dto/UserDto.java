package com.uydev.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uydev.enums.Role;
import com.uydev.validation.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "useName should not be blank")
    @Size(min = 2, max = 50, message = "userName must be 2~50 character long")
    private String userName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must be contain at least one Upper case, one lower case letter, one digit and 8 character long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passWord;

    @NotNull(message = "Role shouldn't be null")
    @ValidEnum(enumClass = Role.class, message = "Invalid role,role should one of 'Admin','Manager', 'User'")
    private String role;
}
