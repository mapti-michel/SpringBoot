package br.acc.webflux.controller;

import br.acc.webflux.entity.User;
import br.acc.webflux.mapper.UserMapper;
import br.acc.webflux.model.request.UserRequest;
import br.acc.webflux.model.response.UserResponse;
import br.acc.webflux.service.UserService;
import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerImplTest {

    public static final long ID = 1L;
    public static final String NOME = "Michel";
    public static final String SENHA = "12345678";


    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    @DisplayName("Test EndPoint save with success")
    void testSaveWithSuccess() {
        final var request = new UserRequest(NOME, SENHA);

        when(userService.save(any(UserRequest.class))).thenReturn(Mono.just(User.builder().build()));

        webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(userService).save(any(UserRequest.class));
    }

    @Test
    @DisplayName("Test EndPoint save with bad request")
    void testSaveWithBadRequest() {
        final var request = new UserRequest(NOME.concat(" "), SENHA);

        webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/users")
                .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST)
                .jsonPath("$.errors").isEqualTo("Validation Error")
                .jsonPath("$.message").isEqualTo("Error on validation attributes")
                .jsonPath("$.errors[0].fieldName").isEqualTo("Error on validation attributes")
                .jsonPath("$.message").isEqualTo("Field cannot have blank spaces at the begining or at end")
        ;

    }

    @Test
    @DisplayName("Test find by id endpoint with success")
    void findByIdWithSuccess() {
        final var userResponse = new UserResponse(ID, NOME, SENHA);

        when(userService.findById(anyLong())).thenReturn(Mono.just(User.builder().build()));
        when(userMapper.toResponse(any(User.class))).thenReturn(userResponse);

        webTestClient.get().uri("/users" + 1L)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.nome").isEqualTo(NOME)
                .jsonPath("$.senha").isEqualTo(SENHA)
        ;
    }

    @Test
    @DisplayName("Test find by id endpoint with bad request")
    void testFindByIdWithBadRequest() {
        final var userResponse = new UserResponse(ID, NOME, SENHA);

//        when(userService.findById(anyLong())).thenReturn(Mono.just(User.builder().build()));
//        when(userMapper.toResponse(any(User.class))).thenReturn(userResponse);

        webTestClient.get().uri("/users" + 1L)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/users")
                .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST)
                .jsonPath("$.errors").isEqualTo("Validation Error")
                .jsonPath("$.message").isEqualTo("Error on validation attributes")
                .jsonPath("$.errors[0].fieldName").isEqualTo("Error on validation attributes")
                .jsonPath("$.message").isEqualTo("Field cannot have blank spaces at the begining or at end")
        ;
    }

    @Test
    @DisplayName("Test findAll endpoint with success")
    void testFindAllWithSuccess() {
        final var userResponse = new UserResponse(ID, NOME, SENHA);

        when(userService.findAll()).thenReturn(Flux.just(User.builder().build()));
        when(userMapper.toResponse(any(User.class))).thenReturn(userResponse);

        webTestClient.get().uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(1L)
                .jsonPath("$.[0].nome").isEqualTo(NOME)
                .jsonPath("$.[0].senha").isEqualTo(SENHA)
        ;

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}