package com.sistema.pedidos.repository;

import com.sistema.pedidos.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>  {



   @Query(value = "SELECT * FROM categoria c WHERE c.ct_nombre LIKE UPPER(CONCAT(?1, '%'))", nativeQuery = true)
    Page<Category> listarCategoryPorNombrePagina(String CT_Nombre, Pageable pageable);

   @Query(value = "select c from Category c where c.estado = true")
    List<Category> listarCategoryActivo();
}
