package com.example.webfluxcourse.mapper;

import com.example.webfluxcourse.entity.User;
import com.example.webfluxcourse.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(final UserRequest userRequest);
}
