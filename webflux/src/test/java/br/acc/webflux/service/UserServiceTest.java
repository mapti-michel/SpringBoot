package br.acc.webflux.service;

import br.acc.webflux.entity.User;
import br.acc.webflux.mapper.UserMapper;
import br.acc.webflux.model.request.UserRequest;
import br.acc.webflux.repository.UserRepository;
import br.acc.webflux.service.excepticon.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Test
    void testSave() {
        UserRequest request = new UserRequest("Michel", "12345678");

        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.save(request);
        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testFindById(){
        when(repository.findById(anyLong())).thenReturn(Mono.just(User
                .builder().id(1L)
                .build()));
        Mono<User> result = service.findById(1L);

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class && user.getId() == 1L)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void testFindAll(){
        when(repository.findAll()).thenReturn(Flux.just(User.builder().build()));
        Flux<User> result = service.findAll();

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findAll();

    }

    @Test
    void testUpdate(){
        UserRequest request = new UserRequest("Michel", "12345678");

        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class), any(User.class))).thenReturn(entity);

        when(repository.findById(anyLong())).thenReturn(Mono.just(User.builder().build()));

        when(repository.save(any(User.class))).thenReturn(Mono.just(entity));

        Mono<User> result = service.update(1L, request);
        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));

    }

    @Test
    void testDelete(){
        User entity = User.builder().build();

        when(repository.findAndRemove(anyLong())).thenReturn(Mono.just(entity));

        Mono<User> result = service.delete(1L);
        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findAndRemove(anyLong());
    }

    @Test
    void testHandleNotFound(){
        User entity = User.builder().build();

        when(repository.findById(anyLong())).thenReturn(Mono.empty());
        
        try{
            service.findById(1L).block();
        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals(format("Object not found. Id: %s, Type: %s", 1L,
                    User.class.getSimpleName()),
                    ex.getMessage());
        }

    }

}