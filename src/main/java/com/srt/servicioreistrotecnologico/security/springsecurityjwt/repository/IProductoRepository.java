package com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository;


import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
