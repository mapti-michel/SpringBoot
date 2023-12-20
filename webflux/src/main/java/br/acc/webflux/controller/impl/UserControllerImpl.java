package br.acc.webflux.controller.impl;

import br.acc.webflux.controller.UserController;
import br.acc.webflux.mapper.UserMapper;
import br.acc.webflux.model.request.UserRequest;
import br.acc.webflux.model.response.UserResponse;
import br.acc.webflux.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping( value = "/users")
public class UserControllerImpl implements UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserControllerImpl(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(Long id) {

        return ResponseEntity
                .ok()
                .body(service.findById(id).map(mapper::toResponse));
//                  OU
//                .body(service.findById(id).map(obj -> mapper.toResponse(obj)));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(Long id, UserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(Long id) {
        return null;
    }
}
