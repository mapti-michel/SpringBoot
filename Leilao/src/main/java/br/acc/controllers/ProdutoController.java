package br.acc.controllers;

import br.acc.entity.Produto;
import br.acc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @RequestMapping(value = "/produtos/all", method = RequestMethod.GET) // =================== LISTA
    private List<Produto> listaProdutos(){
        return produtoRepository.findAll();
    }

    @RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET) // ================== LISTA ESPECÍFICA
    private ResponseEntity findProduo(@PathVariable Long id){
        Produto prod = new Produto();
        prod.setId(id);
        return produtoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/produtos/add", method = RequestMethod.POST) // ==================== POST
    public Produto salvar(@RequestBody Produto prod) {
        return produtoRepository.save(prod);
    }

    @RequestMapping(path = "/produtos/put/{id}", method = RequestMethod.PUT) // ================= PUT
    public ResponseEntity updateProduto(@PathVariable("id") Long id, @RequestBody Produto prod){
        return produtoRepository.findById(id)
                .map(record -> {
                    record.setProduto(prod.getProduto());
                    record.setStatus(prod.getStatus());
                    record.setValorLance(prod.getValorLance());
                    record.setCoduser(prod.getCoduser());
                    Produto up = produtoRepository.save(record);
                    return ResponseEntity.ok().body(up);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/produtos/del/{id}") // ========================= DELETE
    public ResponseEntity<?> deleteProduto(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(record -> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public boolean confereIdCompra(Long idProduto){
        if(produtoRepository.existsByIdProduto(idProduto) != null){
            return false;
        }
        return true;
    }

}
