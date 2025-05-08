package com.example.test_work.service.subscription;

import com.example.test_work.dto.subscription.SubscriptionCreateDto;
import com.example.test_work.dto.subscription.SubscriptionDto;
import com.example.test_work.dto.subscription.SubscriptionMapper;
import com.example.test_work.dto.subscription.SubscriptionTopDto;
import com.example.test_work.exception.NotFoundException;
import com.example.test_work.model.subscription.Subscription;
import com.example.test_work.repository.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public Subscription getSubscriptionBYTitle(String title) {
        Subscription subscription = subscriptionRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Subscription was not found"));
        log.info("Subscription with id {} was found", subscription.getId());
        return subscription;
    }

    @Override
    public SubscriptionDto createSubscription(SubscriptionCreateDto subscriptionCreateDto) {
        Subscription subscription = subscriptionMapper.toSubscription(subscriptionCreateDto);
        subscription = subscriptionRepository.save(subscription);
        log.info("Subscription  {} was created", subscription);
        return subscriptionMapper.toDto(subscription);
    }

    @Override
    public String deleteSubscription(Long subId) {
        Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NotFoundException("Subscription was not found"));
        subscriptionRepository.delete(subscription);
        log.info("Subscription with id {} was deleted", subId);
        return "Subscription with id " + subId + " was deleted";
    }

    @Override
    public Set<SubscriptionTopDto> getTopSubscriptions(Integer limit) {
        Set<SubscriptionTopDto> top = new HashSet<>(subscriptionRepository.getTop(limit));
        log.info("Top {} subscription are {}", limit, top);
        return top;
    }
}
