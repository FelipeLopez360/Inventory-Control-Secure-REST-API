package com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "role")
public class Roles {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String name;


    public Roles get() {
        return null;
    }
}
