package com.example.test_work.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {
    @Size(min = 3, message = "Name min size is - 3")
    private String name;
    @Email(message = "Incorrect format")
    private String email;
    @Size(min = 8, max = 8, message = "Password must be 8 size")
    private String password;
}
