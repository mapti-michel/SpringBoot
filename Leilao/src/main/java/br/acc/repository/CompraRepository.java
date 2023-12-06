package br.acc.repository;

import br.acc.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query(value = "SELECT c.* FROM tbcompra c WHERE c.codproduto = :codProduto", nativeQuery = true)
    List<Compra> existsByCodProduto(@Param("codProduto") Long codProduto);
}
