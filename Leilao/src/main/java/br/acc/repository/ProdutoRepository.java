package br.acc.repository;

import br.acc.entity.Compra;
import br.acc.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT p.* FROM tbproduto p WHERE p.idproduto = :idProduto", nativeQuery = true)
    List<Compra> existsByIdProduto(@Param("idProduto") Long idProduto);

}
