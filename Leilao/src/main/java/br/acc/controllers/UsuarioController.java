package br.acc.controllers;

import br.acc.entity.Usuario;
import br.acc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // GET ALL
    @RequestMapping(value = "/usuarios/all", method = RequestMethod.GET)
    private List<Usuario> listar(){
        return repository.findAll();
    }

    // GET ESPECIFIC
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    private ResponseEntity findUsuario(@PathVariable Long id){
        Usuario user = new Usuario();
        user.setId(id);
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @RequestMapping(path = "/usuarios/add", method = RequestMethod.POST)
    public Usuario salvar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    //PUT
    @RequestMapping(path = "/usuarios/put/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateData(@PathVariable("id") Long id, @RequestBody Usuario user){
        return repository.findById(id)
                .map(record -> {
                    record.setNome(user.getNome());
                    record.setSenha(user.getSenha());
                    Usuario up = repository.save(record);
                    return ResponseEntity.ok().body(up);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    //DELETE
    @DeleteMapping("/usuarios/del/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
