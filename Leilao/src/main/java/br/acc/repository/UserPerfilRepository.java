package br.acc.repository;

import br.acc.entity.UserPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPerfilRepository extends JpaRepository<UserPerfil, Long> {
}
