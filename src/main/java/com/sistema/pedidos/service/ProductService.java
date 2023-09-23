package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.ProductDTO;
import com.sistema.pedidos.DTO.ProductoPlatoDTO;
import com.sistema.pedidos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {


    public ResponseEntity<Object> guardarProduct(ProductDTO product);

    public ResponseEntity<Product> actualizarProduct(ProductDTO product,Long id);

    public Product listarProductPorId(Long id);
    public Page<ProductDTO> listarProductPorPagina(Pageable pageable);
    public List<ProductoPlatoDTO> listarProductPorPagina();
    public List<Product> listarProductPorCombo(Long id);
    public Page<Product> listarProductPorNombrePagina(String nombre,Pageable pageable);

    public void eliminar(Long id);

    public List<ProductDTO> produtosDTOActivos();
}
