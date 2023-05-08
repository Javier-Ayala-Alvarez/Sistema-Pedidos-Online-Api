package com.sistema.pedidos.service;

import com.sistema.pedidos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {


    public ResponseEntity<Object> agregarProduct(Product product);

    public Page<Product> listarProductPorPagina(Pageable pageable);

    public Page<Product> listarProductPorNombrePagina(String nombre,Pageable pageable);

    public void eliminar(Long id);
}
