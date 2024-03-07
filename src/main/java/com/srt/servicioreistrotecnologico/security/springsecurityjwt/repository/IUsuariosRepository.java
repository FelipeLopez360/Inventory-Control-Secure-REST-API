package com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long> {
    // metodo para buscar un usuario por el nombre

    Optional<Usuarios> findByUsername(String username);

    // metodo para poder verificar si existe usuario en base de datos
    boolean existsByUsername(String username);
}
