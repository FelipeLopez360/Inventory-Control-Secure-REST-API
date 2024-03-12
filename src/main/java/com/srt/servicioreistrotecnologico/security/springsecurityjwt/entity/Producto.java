package com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventario")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    private String nobreProducto;
    private String marcaProducto;
    private Long cantidadProdcuto;
    private Long precioProducto;
    private String categoriaProducto;
    private String ubicacionProducto;
    private String notasProducto;
}
