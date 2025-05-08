package com.example.test_work.service.user;

import com.example.test_work.dto.subscription.SubscriptionDto;
import com.example.test_work.dto.user.UserCreateDto;
import com.example.test_work.dto.user.UserResponseDto;
import com.example.test_work.dto.user.UserUpdateDto;
import com.example.test_work.model.user.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto);

    UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId);

    UserResponseDto getUserById(Long userId);

    String deleteUserById(Long userId);

    UserResponseDto addSubscription(Long userId, SubscriptionDto subscriptionCreateDto);

    Set<SubscriptionDto> getSubscriptionsByUserId(Long userId);

    UserResponseDto deleteSubscriptionOfUser(Long userId, Long subId);


}
