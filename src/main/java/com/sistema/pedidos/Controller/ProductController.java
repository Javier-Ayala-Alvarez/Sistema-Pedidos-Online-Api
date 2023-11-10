package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.ProductDTO;
import com.sistema.pedidos.DTO.ProductoPlatoDTO;
import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/new")
    public ResponseEntity<Object> agregarProduct(@RequestBody ProductDTO product){
        return  productService.guardarProduct(product);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Product> actualizarProduct(@RequestBody ProductDTO product,@PathVariable Long id){
        return productService.actualizarProduct(product,id);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Product> listarProductPorId(@PathVariable Long id){

        try{
            Product product=productService.listarProductPorId(id);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/pageables")
    public ResponseEntity<Page<Product>> listarProductPorPagina(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO)int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO)int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO)String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ){

        Page<Product> productPage=productService.listarProductPorPagina(
                PageRequest.of(page,size, Sort.by(order)));
        return new ResponseEntity<Page<Product>>(productPage, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductoPlatoDTO>> listarProductPorPagina() {
        List<ProductoPlatoDTO> productPage = productService.listarProductPorPagina();
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    @GetMapping("/listCombo/{id}")
    public ResponseEntity<List<Product>> listarProductPorCombo(@PathVariable Long id) {
        List<Product> productPage = productService.listarProductPorCombo(id);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/list/search")
    public ResponseEntity<Page<Product>> listarProductPorNombrePagina(
            @RequestParam("product") String nombre,
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO)int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ){
        Page<Product> productPage=productService.listarProductPorNombrePagina(nombre,
                PageRequest.of(page,size));

        return  new ResponseEntity<Page<Product>>(productPage,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarId(@PathVariable Long id){
        productService.eliminar(id);
    }

        @GetMapping("/list/DTO/activo")
    public ResponseEntity<?> listarProductDTOActivo(){
        return ResponseEntity.ok(productService.produtosDTOActivos());
    }

}
