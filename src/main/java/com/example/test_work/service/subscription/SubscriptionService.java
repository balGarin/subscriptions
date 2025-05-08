package com.example.test_work.service.subscription;

import com.example.test_work.dto.subscription.SubscriptionCreateDto;
import com.example.test_work.dto.subscription.SubscriptionDto;
import com.example.test_work.dto.subscription.SubscriptionTopDto;
import com.example.test_work.model.subscription.Subscription;

import java.util.Set;

public interface SubscriptionService {

    Subscription getSubscriptionBYTitle(String title);

    SubscriptionDto createSubscription(SubscriptionCreateDto subscriptionCreateDto);

    String deleteSubscription(Long subId);

    Set<SubscriptionTopDto> getTopSubscriptions(Integer limit);
}
