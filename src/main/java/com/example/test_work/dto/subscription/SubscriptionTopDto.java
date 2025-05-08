package com.example.test_work.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscriptionTopDto {
    private String title;
    private Long rate;
}
