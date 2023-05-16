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
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements CategoryService {

     @Autowired
     private CategoryRepository categoryRepository;



    @Override
    public Category guardarCategory(Category category) {
        return  categoryRepository.save(category);

    }

    @Override
    public ResponseEntity<Category> actualizarCategory(Category category, Long id) {

        Optional<Category>categoryOptional=categoryRepository.findById(id);
        if(!categoryOptional.isPresent()){
            return  ResponseEntity.unprocessableEntity().build();
        }

        category.setCT_Nombre(category.getCT_Nombre());
        category.setCT_Estado(category.isCT_Estado());
        category.setId(categoryOptional.get().getId());

        Category categoryGuardado=categoryRepository.save(category);
        return ResponseEntity.ok(categoryGuardado);
    }


    @Override
    public List<Category> listarCategoryActivo() {
        return new ArrayList<>(categoryRepository.listarCategoryActivo());
    }

    @Override
    public List<Category> listarCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category listarCategoryPorId(Long id) {
        return categoryRepository.findById(id).get();
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
