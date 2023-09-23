package com.sistema.pedidos.service.impl;


import com.sistema.pedidos.DTO.ProductDTO;
import com.sistema.pedidos.DTO.ProductoPlatoDTO;
import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.repository.CategoryRepository;
import com.sistema.pedidos.repository.ProductRepository;
import com.sistema.pedidos.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> guardarProduct(ProductDTO product) {
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Product product1 = new Product();
       product1.setId(productRepository.idMaximo()+1);
        product1.setNombre(product.getNombre());
        product1.setDescripcion(product.getDescripcion());
        product1.setEstado(product.isEstado());
        product1.setPrecioVenta(product.getPrecioVenta());
        product1.setGanancia(product.getGanancia());
        product1.setUrlImagen(product.getUrlImagen());
        product1.setCategory(categoryOptional.get());

        Product productGuardado = productRepository.save(product1);
        return ResponseEntity.ok(productGuardado);
    }

    @Override
    public ResponseEntity<Product> actualizarProduct(ProductDTO product, Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
        Optional<Product> productOptional = productRepository.findById(id);

        if (!categoryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!productOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
    Product product1 = new Product();
        product1.setId(productOptional.get().getId());
        product1.setCategory(categoryOptional.get());

        product1.setNombre(product.getNombre());
        product1.setDescripcion(product.getDescripcion());
        product1.setEstado(product.isEstado());
        product1.setPrecioVenta(product.getPrecioVenta());
        product1.setGanancia(product.getGanancia());
        product1.setUrlImagen(product.getUrlImagen());

        Product productGuardado = productRepository.save(product1);

        return ResponseEntity.ok(productGuardado);
    }

    @Override
    public Product listarProductPorId(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> listarProductPorPagina(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


     /*   public Page<ProductDTO> listarProductPorPagina(Pageable pageable) {
            Page<Product> productPage = productRepository.findAll(pageable);

            List<ProductDTO> productDTOList = productPage
                    .getContent()
                    .stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());

            return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
        }
*/



    @Override
    public List<ProductoPlatoDTO> listarProductPorPagina() {
        List<Map<String, Object>> resultados = productRepository.listarProductPorPagina();
        List<ProductoPlatoDTO> productos = new ArrayList<>();
        for (Map<String, Object> resultado : resultados) {
            ProductoPlatoDTO producto = new ProductoPlatoDTO();
            producto.setIdProducto((BigInteger) resultado.get("idProducto"));
            producto.setNombre((String) resultado.get("nombre"));
            producto.setDescripcion((String) resultado.get("descripcion"));
            producto.setPrecioVenta((Double) resultado.get("precioVenta"));
            producto.setGanancia((Double) resultado.get("ganancia"));
            producto.setEstado((Boolean) resultado.get("estado"));
            producto.setUrlImagen((String) resultado.get("url_imagen"));
            producto.setEvento((String) resultado.get("evento"));
            producto.setPromocion((BigInteger) resultado.get("promocion"));
            producto.setIdCombo((BigInteger) resultado.get("idCombo"));
            producto.setNombreProducto((String) resultado.get("nombreProducto"));
            producto.setUrlImagenProducto((String) resultado.get("urlImagenProducto"));
            producto.setCategory((BigInteger) resultado.get("categoria_id"));
            productos.add(producto);
        }

        return productos;

        // return productRepository.listarProductPorPagina();
    }

    @Override
    public List<Product> listarProductPorCombo(Long id) {
        return (List<Product>) productRepository.listarProductPorCombo(id);
    }

    @Override
    public Page<Product> listarProductPorNombrePagina(String nombre, Pageable pageable) {
        return productRepository.listarProductPorNombrePagina(nombre, pageable);
    }

    @Override
    public void eliminar(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> produtosDTOActivos() {
        List<Product> productList = productRepository.listarProductosActivos();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            ProductDTO productDTO = mapearDTO(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    private Product mapearEntidad(ProductDTO productDTO) {
        Product producto = modelMapper.map(productDTO, Product.class);
        return producto;
    }

    private ProductDTO mapearDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }
}
