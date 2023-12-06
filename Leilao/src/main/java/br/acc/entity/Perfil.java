package br.acc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbperfil")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idperfil")
        private Long id;

        @Column(name = "perfil")
        private String perfil;

}
