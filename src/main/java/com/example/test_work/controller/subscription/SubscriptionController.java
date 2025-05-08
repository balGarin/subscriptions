package com.example.test_work.controller.subscription;

import com.example.test_work.dto.subscription.SubscriptionCreateDto;
import com.example.test_work.service.subscription.SubscriptionService;
import com.example.test_work.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final UserService userService;
    private final SubscriptionService subscriptionService;


    @PostMapping
    public ResponseEntity<Object> createSubscription(@RequestBody @Valid SubscriptionCreateDto subscriptionCreateDto) {
        return ResponseEntity.status(201).body(subscriptionService.createSubscription(subscriptionCreateDto));
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Long subId) {
        return ResponseEntity.status(200).body(subscriptionService.deleteSubscription(subId));

    }

    @GetMapping
    public ResponseEntity<Object> getTopSubscriptions(@RequestParam(required = false, defaultValue = "3") Integer limit) {
        return ResponseEntity.status(200).body(subscriptionService.getTopSubscriptions(limit));

    }

}
