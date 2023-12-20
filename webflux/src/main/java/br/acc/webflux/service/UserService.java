package br.acc.webflux.service;

import br.acc.webflux.entity.User;
import br.acc.webflux.mapper.UserMapper;
import br.acc.webflux.model.request.UserRequest;
import br.acc.webflux.repository.UserRepository;
import br.acc.webflux.service.excepticon.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request){

        return repository.save(mapper.toEntity(request));
    }

    public Mono<User> findById(final Long id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error((
                        new ObjectNotFoundException(
                                format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                        ))
                ));

    }

    public Flux<User> findAll(){
        return repository.findAll();
    }

    public Mono<User> update(final Long id, final UserRequest request){
        return findById(id)
                .map(entity -> mapper.toEntity(request, entity))
                .flatMap(repository::save)
                ;
    }

    public Mono<User> delete(Long id) {
        return handleNotFound(repository.findAndRemove(id), id);
    }

    private <T> Mono<T> handleNotFound(Mono<T> mono, Long id){ // Método genérico ou Mono<T> mono, Long id, Class<T> clazz
        return mono
                .switchIfEmpty(Mono.error((
                        new ObjectNotFoundException(
                                format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                        ))
                ));
    }

}
