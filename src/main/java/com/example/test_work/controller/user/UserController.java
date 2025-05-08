package com.example.test_work.controller.user;

import com.example.test_work.dto.subscription.SubscriptionDto;
import com.example.test_work.dto.user.UserCreateDto;
import com.example.test_work.dto.user.UserUpdateDto;
import com.example.test_work.service.subscription.SubscriptionService;
import com.example.test_work.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return ResponseEntity.status(201).body(userService.createUser(userCreateDto));

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto,
                                             @PathVariable Long userId) {
        return ResponseEntity.status(201).body(userService.updateUser(userUpdateDto, userId));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(userService.getUserById(userId));
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(userService.deleteUserById(userId));

    }

    @PostMapping("/{userId}/subscriptions")
    public ResponseEntity<Object> addSubscription(@PathVariable Long userId,
                                                  @RequestBody SubscriptionDto subscriptionDto) {
        return ResponseEntity.status(201).body(userService.addSubscription(userId, subscriptionDto));
    }

    @GetMapping("/{userId}/subscriptions")
    public ResponseEntity<Object> getSubscriptionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(userService.getSubscriptionsByUserId(userId));

    }

    @DeleteMapping("/{userId}/subscriptions/{subId}")
    public ResponseEntity<Object> deleteSubscriptionOfUser(@PathVariable Long userId, @PathVariable Long subId) {
        return ResponseEntity.status(200).body(userService.deleteSubscriptionOfUser(userId, subId));

    }


}
