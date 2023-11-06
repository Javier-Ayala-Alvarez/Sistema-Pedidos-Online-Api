package com.sistema.pedidos.Controller;

import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.Sucursal;
import com.sistema.pedidos.service.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursal")
public class BranchOfficeController {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @PostMapping("/new")
    public ResponseEntity<Object> guardarSucursal(@RequestBody Sucursal sucursal){
        return branchOfficeService.guardarSucursal(sucursal);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(@RequestBody Sucursal sucursal,@PathVariable Long id){
        return  branchOfficeService.actualizarSucursal(sucursal,id);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Sucursal> listarSucursalPorId(@PathVariable Long id){
        try{
            Sucursal sucursal=branchOfficeService.listarSucursalPorId(id);
            return new ResponseEntity<Sucursal>(sucursal,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Sucursal>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/list/activo")
    public ResponseEntity<?> listarSucursalActivo(){
        return ResponseEntity.ok(branchOfficeService.listarSucursalActivo());
    }
    @GetMapping("/list")
    public List<Sucursal> listarSucursal(){
        return branchOfficeService.listarSucursal();
    }



    @GetMapping("/list/pageables")
    public ResponseEntity<Page<Sucursal>> listarSucursalPorPagina(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO)int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO)int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO)String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ){
        Page<Sucursal> sucursalPage=branchOfficeService.listarSucursalPorPagina(
                PageRequest.of(page,size, Sort.by(order)));
        return new ResponseEntity<Page<Sucursal>>(sucursalPage, HttpStatus.OK);
    }

    @GetMapping("/list/search")
    public ResponseEntity<Page<Sucursal>> listarSucursalPorNombrePagina(
            @RequestParam("sucursal") String nombre,
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO)int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ){
        Page<Sucursal> sucursalPage=branchOfficeService.listarSucursalPorNombrePagina(nombre,
                PageRequest.of(page,size));
        return new ResponseEntity<Page<Sucursal>>(sucursalPage,HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public void eliminarId(@PathVariable Long id){
        branchOfficeService.eliminar(id);
    }

    @GetMapping("/usuario/id/{id}")
    public ResponseEntity<Sucursal> getsucursalByIdUsuario(@PathVariable Long id){
        try{
            Sucursal sucursal=branchOfficeService.getsucursalByIdUsuario(id);
            return new ResponseEntity<Sucursal>(sucursal,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Sucursal>(HttpStatus.NOT_FOUND);
        }

    }

}
