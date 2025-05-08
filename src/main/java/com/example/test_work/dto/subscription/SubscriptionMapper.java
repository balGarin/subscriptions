package com.example.test_work.dto.subscription;

import com.example.test_work.model.subscription.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    @Mapping(target = "id",ignore = true)
    Subscription toSubscription(SubscriptionCreateDto subscriptionCreateDto);

    SubscriptionDto toDto(Subscription subscription);

    Set<SubscriptionDto> toDto(Set<Subscription>subscriptions);
}
