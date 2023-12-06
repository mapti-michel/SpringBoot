package br.acc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbcompra")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompra")
    private Long id;

    @Column(name = "valor")
    private Double valor;


    @Column(name = "codproduto")
    private Long codProduto;

/*
    @Enumerated(EnumType.STRING)
    @Column(name = "codproduto")
    Produto produto;
*/

}
