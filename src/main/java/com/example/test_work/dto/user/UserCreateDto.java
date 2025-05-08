package com.example.test_work.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, message = "Name min size is - 3")
    private String name;
    @Email(message = "Incorrect format")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 8, message = "Password must be 8 size")
    private String password;
}
