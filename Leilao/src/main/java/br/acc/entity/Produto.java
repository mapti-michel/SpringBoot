package br.acc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbproduto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduto")
    private Long id;

    @Column(name = "produto")
    private String produto;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "lanceinicial")
    private Double valorLance;

    @Column(name = "coduser")
    private Long coduser;

/*

    @OneToOne
    @JoinColumn(name = "coduser")
    private Usuario coduser;

*/
}
