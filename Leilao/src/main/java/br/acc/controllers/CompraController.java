package br.acc.controllers;

import br.acc.entity.Compra;
import br.acc.repository.CompraRepository;
import br.acc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CompraController {

    private boolean codProduto;
    private boolean existe;
    private ProdutoController produtoController;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(value = "/compras/all", method = RequestMethod.GET)
    private List<Compra> listaCompra(){
        return compraRepository.findAll();
    }

    @RequestMapping(value = "/compras/{id}", method = RequestMethod.GET)
    private ResponseEntity findCompras(@PathVariable Long id){
        Compra compra = new Compra();
        compra.setId(id);
        return compraRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/compras/add", method = RequestMethod.POST)
    public ResponseEntity salvar(@RequestBody Compra compra) {
        existe = false;

        codProduto = produtoController.confereIdCompra(compra.getCodProduto());
        existe = this.confereCodCompra(compra.getCodProduto()); // Valida se já existe na compra

        if(codProduto){
            if(existe){
                compraRepository.save(compra);// Realiza o POST
                return ResponseEntity
                        .status(Response.Status.CREATED.getStatusCode())
                        .body("Salvo com sucesso");
            }else{
                return ResponseEntity
                        .status(Response.Status.CONFLICT.getStatusCode())
                        .body("O produto já foi vendido");
            }
        }else{
            return ResponseEntity
                    .status(Response.Status.CONFLICT.getStatusCode())
                    .body("Produto inexistente");
        }
    }


    @RequestMapping(path = "/compras/put/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCompras(@PathVariable("id") Long id, @RequestBody Compra compra){
        return compraRepository.findById(id)
                .map(record -> {
                    record.setValor(compra.getValor());
                    record.setCodProduto(compra.getCodProduto());
                    Compra up = compraRepository.save(record);
                    return ResponseEntity.ok().body(up);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/compras/del/{id}")
    public ResponseEntity<?> deleteCompra(@PathVariable Long id){
        return compraRepository.findById(id)
                .map(record -> {
                    compraRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public boolean confereCodCompra(Long compraCodProduto){
        if(compraRepository.existsByCodProduto(compraCodProduto) != null){
            return false;
        }
        return true;
    }

}
