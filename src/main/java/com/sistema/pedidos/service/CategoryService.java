package com.sistema.pedidos.service;

import com.sistema.pedidos.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {


    public Category guardarCategory(Category category);

    public ResponseEntity<Category> actualizarCategory(Category category,Long id);


    public List<Category> listarCategoryActivo();

    public List<Category> listarCategory();

    public Category listarCategoryPorId(Long id);

    public Page<Category> listarCategoryPorPagina(Pageable pageable);

    public Page<Category> listarCategoryPorNombrePagina(String CT_Nombre,Pageable pageable );

    public void eliminarCategory(Long id);



}
