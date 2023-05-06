package com.sistema.pedidos.service;

import com.sistema.pedidos.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {


    public Category guardarCategory(Category category);

    public Category actualizarCategory(Category category,Long id);

    public Page<Category> listarCategoryPorPagina(Pageable pageable);

    public Page<Category> listarCategoryPorNombrePagina(String CT_Nombre,Pageable pageable );

    public void eliminarCategory(Long id);



}
