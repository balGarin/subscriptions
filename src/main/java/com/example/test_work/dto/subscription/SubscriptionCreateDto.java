package com.example.test_work.dto.subscription;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionCreateDto {
    @NotNull(message = "Subscription's title must be present")
    @NotBlank(message = "Title can't be empty")
    private String title;
}
