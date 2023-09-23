package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.CategoriaDTO;
import com.sistema.pedidos.DTO.PuestoDTO;
import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.PuestoLaboralEntity;
import com.sistema.pedidos.repository.CategoryRepository;
import com.sistema.pedidos.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;


    @Override
    public Category guardarCategory(Category category) {
        return categoryRepository.save(category);

    }

    @Override
    public ResponseEntity<Category> actualizarCategory(Category category, Long id) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        category.setNombre(category.getNombre());
        category.setEstado(category.getEstado());
        category.setId(categoryOptional.get().getId());

        Category categoryGuardado = categoryRepository.save(category);
        return ResponseEntity.ok(categoryGuardado);
    }


    @Override
    public List<Category> listarCategoryActivo() {
        return new ArrayList<>(categoryRepository.listarCategoryActivo());
    }

    public List<CategoriaDTO> listarCategoriaDTOActivo() {
        List<Category> categoryList = categoryRepository.listarCategoryActivo();
        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoriaDTO categoriaDTO = mapearDTO(category);
            categoriaDTOList.add(categoriaDTO);
        }
        return categoriaDTOList;
    }

    @Override
    public List<Category> listarCategory() {
        return categoryRepository.listarCategoryActivo();
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
        return categoryRepository.listarCategoryPorNombrePagina(CT_Nombre, pageable);
    }

    @Override
    public void eliminarCategory(Long id) {
        categoryRepository.deleteById(id);
    }


    // Convierte de DTO a Entidad
    private Category mapearEntidad(CategoriaDTO categoriaDTO) {
        Category categoria = modelMapper.map(categoriaDTO, Category.class);
        return categoria;
    }

    private CategoriaDTO mapearDTO(Category category) {
        CategoriaDTO categoriaDTO = modelMapper.map(category, CategoriaDTO.class);
        return categoriaDTO;
    }

}
