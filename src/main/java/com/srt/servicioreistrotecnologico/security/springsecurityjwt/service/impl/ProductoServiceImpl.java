package com.srt.servicioreistrotecnologico.security.springsecurityjwt.service.impl;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Producto;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository.IProductoRepository;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.service.IProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;
    private List<Producto> producto = new ArrayList<>();
    @Override
    public List<Producto> listarProductos() {
        return producto = iProductoRepository.findAll();
    }
    @Override
    public Producto guardarProducto(Producto producto) {
        return iProductoRepository.save(producto);
    }
    @Override
    public Producto buscarProductoPorId(Long idProducto) {
        Producto producto = iProductoRepository.findById(idProducto).orElse(null);
        return producto;
    }
    @Override
    public Producto eliminarProducto(Long idProducto) {
        iProductoRepository.deleteById(idProducto);
        return null;
    }
}
