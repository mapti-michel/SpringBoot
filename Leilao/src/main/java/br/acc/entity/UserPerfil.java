package br.acc.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbuserperfil")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coduser")
    private Long coduser;

    @Column(name = "codperfil")
    private Long codperfil;
/*

    @OneToOne
    @JoinColumn(name = "coduser")
    Usuario user;

    @OneToOne
    @JoinColumn(name = "codperfil")
    Perfil perfil;

*/

}
