package br.acc.controllers;


import br.acc.repository.UserPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeilaoEndpoints { // Também controller

    @Autowired
    private UserPerfilRepository userPerfilRepository;


    // HOME
    @RequestMapping("/")
    public String hello(){
        return "Bem-vindo ao leilão";
    }


/*    @RequestMapping("/resource")
    public Map<String, Object> home(){
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }*/



/*
    ==============================================================================================
    Listar todos os contatos - @GetMapping(“/contacts)
    Obter um contato específico pelo ID - @GetMapping(“/contacts/{id}”)
    Remover um contato pelo ID - @DeleteMapping(“/contacts/{id}”)
    Criar um novo contato - @PostMapping(“/contacts)
    Atualizar detalhes de um contato - @PutMapping(“/contacts/{id}”)
    ==============================================================================================
 */


}
