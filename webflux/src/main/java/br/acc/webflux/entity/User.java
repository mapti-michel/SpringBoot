package br.acc.webflux.entity;



import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@Entity
@Table(name = "tbuser")
public class User {

    @Id
    private Long id;

    private String nome;

    @Indexed(unique = true) // Informa que seja único
    private String senha;

}
