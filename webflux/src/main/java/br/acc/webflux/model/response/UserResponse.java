package br.acc.webflux.model.response;


import lombok.Data;

@Data
public class UserResponse {

    Long id;
    String nome;
    String senha;

    public UserResponse() {

    }

    public UserResponse(Long id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
}
