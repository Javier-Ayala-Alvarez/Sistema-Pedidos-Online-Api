package com.sistema.pedidos.Controller;

import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @PostMapping("/new")
    public Category guardarCategory(@RequestBody Category category){
        return categoryService.guardarCategory(category);
    }


    @GetMapping("/list/pageables")
    public ResponseEntity<Page<Category>> listarCategoryPorPagina(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO)int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO)int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO)String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO)boolean asc
    ){
        Page<Category> categoryPage=categoryService.listarCategoryPorPagina(
                PageRequest.of(page,size, Sort.by(order)));

        return new ResponseEntity<Page<Category>>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("/list/search")
    public ResponseEntity<Page<Category>>  listarCategoryPorNombrePagina(
            @RequestParam("category")String cat_Nombre,
            @RequestParam(defaultValue =ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue =ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue =ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc)
    {
        Page<Category> categoryPage=categoryService.listarCategoryPorNombrePagina(cat_Nombre,
                PageRequest.of(page,size));
        return  new ResponseEntity<Page<Category>>(categoryPage,HttpStatus.OK);
    }

    @GetMapping("/list/activo")
    public ResponseEntity<?> listarCategoryActivo(){
        return ResponseEntity.ok(categoryService.listarCategoryActivo());
    }

    @DeleteMapping("/delete/{id}")
    public  void eliminarId(@PathVariable Long id){
        categoryService.eliminarCategory(id);
    }




}
