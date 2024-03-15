package com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {

    // metodo para buscar un rol por nombre en la base de datos
    Roles findByName(String name);

}
