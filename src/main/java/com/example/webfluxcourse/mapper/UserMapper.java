package com.example.webfluxcourse.mapper;

import com.example.webfluxcourse.entity.User;
import com.example.webfluxcourse.model.request.UserRequest;
import com.example.webfluxcourse.model.response.UserResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(final UserRequest userRequest);
    @Mapping(target = "id", ignore = true)
    User toUser(final UserRequest userRequest, @MappingTarget User user);

    UserResponse toUserResponse(final User entity);
}
