package com.example.webfluxcourse.service;

import com.example.webfluxcourse.entity.User;
import com.example.webfluxcourse.mapper.UserMapper;
import com.example.webfluxcourse.model.request.UserRequest;
import com.example.webfluxcourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<User> save(final UserRequest userRequest) {
        return userRepository.save(userMapper.toUser(userRequest));

    }
}
