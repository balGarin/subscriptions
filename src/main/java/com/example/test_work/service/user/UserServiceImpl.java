package com.example.test_work.service.user;

import com.example.test_work.dto.subscription.SubscriptionDto;
import com.example.test_work.dto.subscription.SubscriptionMapper;
import com.example.test_work.dto.user.UserCreateDto;
import com.example.test_work.dto.user.UserMapper;
import com.example.test_work.dto.user.UserResponseDto;
import com.example.test_work.dto.user.UserUpdateDto;
import com.example.test_work.exception.NotFoundException;
import com.example.test_work.model.subscription.Subscription;
import com.example.test_work.model.user.User;
import com.example.test_work.repository.user.UserRepository;
import com.example.test_work.service.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;

    private final SubscriptionService subscriptionService;

    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.toUser(userCreateDto);
        user = userRepository.save(user);
        log.info("User  {} was created", user);
        return userMapper.toDto(user);
    }


    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        User user = getUser(userId);
        if (userUpdateDto.getName() != null) user.setName(userUpdateDto.getName());
        if (userUpdateDto.getEmail() != null) user.setEmail(userUpdateDto.getEmail());
        if (userUpdateDto.getPassword() != null) user.setPassword(userUpdateDto.getPassword());
        user = userRepository.save(user);
        log.info("User  {} was updated", user);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = getUser(userId);
        log.info("User  {} was found", user);
        return userMapper.toDto(user);
    }

    @Override
    public String deleteUserById(Long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
        log.info("User  with id {} was deleted", user);
        return "User with Id " + userId + " was deleted";
    }

    @Override
    public UserResponseDto addSubscription(Long userId, SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionService.getSubscriptionBYTitle(subscriptionDto.getTitle());
        User user = getUser(userId);
        user.getSubscriptions().add(subscription);
        user = userRepository.save(user);
        log.info("Subscription with title {} was added to User with id {}", subscriptionDto.getTitle(), userId);
        return userMapper.toDto(user);
    }

    @Override
    public Set<SubscriptionDto> getSubscriptionsByUserId(Long userId) {
        User user = getUser(userId);
        Set<Subscription> subscriptions = user.getSubscriptions();
        log.info("Subscriptions of user with id {} was found", userId);
        return subscriptionMapper.toDto(subscriptions);
    }

    @Override
    public UserResponseDto deleteSubscriptionOfUser(Long userId, Long subId) {
        User user = getUser(userId);
        Set<Subscription> subscriptions = user.getSubscriptions();
        subscriptions = subscriptions.stream()
                .filter(subscription -> !subscription.getId().equals(subId)).collect(Collectors.toSet());
        user.setSubscriptions(subscriptions);
        user = userRepository.save(user);
        log.info("Subscription with id {} was deleted from User with id {}", subId, userId);
        return userMapper.toDto(user);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User was not found"));
    }
}
