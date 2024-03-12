package com.srt.servicioreistrotecnologico.security.springsecurityjwt.service;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> listarProductos();
    public Producto guardarProducto(Producto producto);
    public Producto buscarProductoPorId(Long idProducto);
    public Producto eliminarProducto(Long idProducto);

}
