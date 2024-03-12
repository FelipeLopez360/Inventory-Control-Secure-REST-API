package com.srt.servicioreistrotecnologico.security.springsecurityjwt.controller;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Producto;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class RestControllerProducto {

    @Autowired
    private ProductoServiceImpl productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    public  ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    @GetMapping("/buscar/{idProducto}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long idProducto){
        Producto producto = productoService.buscarProductoPorId(idProducto);
        
        if (producto != null){
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/editar/{idProducto}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Long idProducto, @RequestBody Producto producto) {
        Producto existeProducto = productoService.buscarProductoPorId(idProducto);

        if (producto != null) {
            existeProducto.setNobreProducto(producto.getNobreProducto());
            existeProducto.setMarcaProducto(producto.getMarcaProducto());
            existeProducto.setCantidadProdcuto(producto.getCantidadProdcuto());
            existeProducto.setPrecioProducto(producto.getPrecioProducto());
            existeProducto.setCategoriaProducto(producto.getCategoriaProducto());
            existeProducto.setUbicacionProducto(producto.getUbicacionProducto());
            existeProducto.setNotasProducto(producto.getNotasProducto());

            Producto productoActualizado = productoService.guardarProducto(existeProducto);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long idProducto){
        productoService.eliminarProducto(idProducto);

        if (idProducto != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
