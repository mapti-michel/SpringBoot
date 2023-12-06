package br.acc.controllers;

import br.acc.entity.Lance;
import br.acc.repository.LanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class LanceController {

    private boolean codCompra;
    private boolean coduser;

    @Autowired
    private LanceRepository lanceRepository;

    @RequestMapping(value = "/lances/all", method = RequestMethod.GET) // =================== LISTA
    private List<Lance> listaLance(){
        return lanceRepository.findAll();
    }

    @RequestMapping(value = "/lances/{id}", method = RequestMethod.GET) // ================== LISTA ESPECÍFICA
    private ResponseEntity findLance(@PathVariable Long id){
        Lance lance = new Lance();
        lance.setId(id);
        return lanceRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/lances/add", method = RequestMethod.POST)
    public ResponseEntity salvarLances(@RequestBody Lance lance) {




        return ResponseEntity
                .status(Response.Status.CREATED.getStatusCode())
                .body("Criado com sucesso");
    }

    @RequestMapping(path = "/lances/put/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateLances(@PathVariable("id") Long id, @RequestBody Lance lance){
        return lanceRepository.findById(id)
                .map(record -> {
                    record.setDataHora(lance.getDataHora());
                    record.setValorLance(lance.getValorLance());
                    record.setCodcompra(lance.getCodcompra());
                    record.setCoduser(lance.getCoduser());
                    Lance up = lanceRepository.save(record);
                    return ResponseEntity.ok().body(up);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/lances/del/{id}") // ========================= DELETE
    public ResponseEntity<?> deleteLance(@PathVariable Long id){
        return lanceRepository.findById(id)
                .map(record -> {
                    lanceRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
