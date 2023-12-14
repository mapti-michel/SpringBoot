package br.acc.webflux.model.response;


import lombok.Data;

@Data
public class UserResponse {

    Long id;
    String nome;
    String senha;
}
