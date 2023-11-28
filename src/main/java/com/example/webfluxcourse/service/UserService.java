package com.example.webfluxcourse.service;

import com.example.webfluxcourse.entity.User;
import com.example.webfluxcourse.mapper.UserMapper;
import com.example.webfluxcourse.model.request.UserRequest;
import com.example.webfluxcourse.repository.UserRepository;
import com.example.webfluxcourse.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<User> save(final UserRequest userRequest) {
        return userRepository.save(userMapper.toUser(userRequest));

    }

    public Mono<User> findById(final String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException(format("Object not found. Id: %s, Type: %s",
                        id, User.class.getSimpleName()))));
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> update(final String id, final UserRequest userRequest) {
        return findById(id)
                .map(user ->  userMapper.toUser(userRequest, user))
                .flatMap(userRepository::save);

    }
}
