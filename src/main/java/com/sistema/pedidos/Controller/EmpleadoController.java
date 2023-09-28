package com.sistema.pedidos.Controller;

import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.service.EmpleadoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Empleado>> listarEmpleados() {
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/pageable")
    public ResponseEntity<Page<Empleado>> listarEmpleadosPorPagina(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO) String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<Empleado> empleadoPage = empleadoService.getAllWithPagination(PageRequest.of(page, size, Sort.by(order)));
        return new ResponseEntity<>(empleadoPage, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado) {
        return new ResponseEntity<>(empleadoService.save(empleado), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Integer id) {
        empleadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoService.save(empleado);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }

    @PatchMapping("/baja/{id}")
    public ResponseEntity darDeBajaEmpleado(@PathVariable Integer id) {
        return empleadoService.darDeBajaEmpleado(id);
    }

    @GetMapping("/list/search")
    public ResponseEntity<Page<Empleado>> listarEmpleadosPorNombrePagina(
            @RequestParam("empleado")String emp_Nombre,
            @RequestParam(defaultValue =ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue =ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<Empleado> EmpleadoPage=empleadoService.listarEmpleadoPorNombrePagina(emp_Nombre,
                PageRequest.of(page,size));
        return  new ResponseEntity<Page<Empleado>>(EmpleadoPage,HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Empleado> listarEmpleadoPorId(@PathVariable int id){
        try {
            Empleado empleado=empleadoService.listarEmpleadoPorId(id);
            return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND);
        }
    }
}


