package com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String username;
    private String password;
    //*  (fetch.eager) cada que consulta a la base de datos trae todos los roles
    //* (cascade.all) cuando elimina un usuario elimina toda la data
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id_usuario")
            ,inverseJoinColumns = @JoinColumn (name = "role_id",referencedColumnName = "id_role"))
    private List<Roles> roles = new ArrayList<>();
}
