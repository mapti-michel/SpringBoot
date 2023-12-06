package br.acc.controllers;

import br.acc.entity.Perfil;
import br.acc.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @RequestMapping(value = "/perfils/all", method = RequestMethod.GET) // =================== LISTA
    private List<Perfil> listaPerfil(){
        return perfilRepository.findAll();
    }

    @RequestMapping(value = "/perfis/{id}", method = RequestMethod.GET) // ================== LISTA ESPECÍFICA
    private ResponseEntity findPerfil(@PathVariable Long id){
        Perfil perfil = new Perfil();
        perfil.setId(id);
        return perfilRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/perfis/add", method = RequestMethod.POST) // ==================== POST
    public Perfil salvar(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }
    @RequestMapping(path = "/perfis/put/{id}", method = RequestMethod.PUT) // ================= PUT
    public ResponseEntity updatePerfil(@PathVariable("id") Long id, @RequestBody Perfil perfil){
        return perfilRepository.findById(id)
                .map(record -> {
                    record.setPerfil(perfil.getPerfil());
                    Perfil up = perfilRepository.save(record);
                    return ResponseEntity.ok().body(up);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/perfis/del/{id}") // ========================= DELETE
    public ResponseEntity<?> deletePerfil(@PathVariable Long id){
        return perfilRepository.findById(id)
                .map(record -> {
                    perfilRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
