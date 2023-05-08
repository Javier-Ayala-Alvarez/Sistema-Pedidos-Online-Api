package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.repository.CategoryRepository;
import com.sistema.pedidos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {

     @Autowired
     private CategoryRepository categoryRepository;



    @Override
    public Category guardarCategory(Category category) {
        return  categoryRepository.save(category);

    }

    @Override
    public List<Category> listarCategoryActivo() {
        return new ArrayList<>(categoryRepository.listarCategoryActivo());
    }

    @Override
    public Category actualizarCategory(Category category, Long id) {

        Category existenteCategory=categoryRepository.findById(id).get();

        existenteCategory.setCT_Nombre(category.getCT_Nombre());
        existenteCategory.setCT_Estado(category.isCT_Estado());
        Category categoryGuardado=categoryRepository.save(existenteCategory);
        return new  ResponseEntity<Category>(categoryGuardado, HttpStatus.OK).getBody();
    }

    @Override
    public Page<Category> listarCategoryPorPagina(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> listarCategoryPorNombrePagina(String CT_Nombre, Pageable pageable) {
        return categoryRepository.listarCategoryPorNombrePagina(CT_Nombre,pageable);
    }

    @Override
    public void eliminarCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
