package com.example.test_work.dto.user;

import com.example.test_work.dto.subscription.SubscriptionMapper;
import com.example.test_work.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = SubscriptionMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreateDto userCreateDto);

    UserResponseDto toDto(User user);


}
