package com.sistema.pedidos.service.impl;


import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.repository.CategoryRepository;
import com.sistema.pedidos.repository.ProductRepository;
import com.sistema.pedidos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<Object> agregarProduct(Product product) {
        Optional<Category> categoryOptional=categoryRepository.findById(product.getCategory().getId());
        if(!categoryOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        product.setNombre(product.getNombre());
        product.setDescripcion(product.getDescripcion());
        product.setEstado(product.isEstado());
        product.setPrecioVenta(product.getPrecioVenta());
        product.setGanancia(product.getGanancia());
        product.setUrlImagen(product.getUrlImagen());
        product.setCategory(categoryOptional.get());

        Product productGuardado=productRepository.save(product);
        return ResponseEntity.ok(productGuardado);
    }

    @Override
    public Page<Product> listarProductPorPagina(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> listarProductPorNombrePagina(String nombre, Pageable pageable) {
        return productRepository.listarProductPorNombrePagina(nombre,pageable);
    }

    @Override
    public void eliminar(Long id) {
        productRepository.deleteById(id);
    }
}
