package com.example.test_work.dto.user;

import com.example.test_work.dto.subscription.SubscriptionDto;
import com.example.test_work.model.subscription.Subscription;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private Set<SubscriptionDto> subscriptions;
}
