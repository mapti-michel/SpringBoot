package br.acc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "tblance")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Lance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlance")
    private Long id;

    @Column(name = "dataHora")
    private Date dataHora;

    @Column(name = "valorLance")
    private Double valorLance;

    @OneToOne
    @JoinColumn(name = "codcompra")
    private Long codcompra;

    @OneToOne
    @JoinColumn(name = "coduser")
    private Long coduser;


/*

    @OneToOne
    @JoinColumn(name = "codcompra")
    Compra compra;

    @OneToOne
    @JoinColumn(name = "coduser")
    Usuario user;

*/



}
