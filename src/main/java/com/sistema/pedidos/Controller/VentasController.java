package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.VentasDTO;
import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.service.VentasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("venta")
@CrossOrigin("*")
public class VentasController {
    @Autowired
    VentasServices ventasServices;

    @PostMapping("/guardar")
    public ResponseEntity<VentasDTO> guardar(@Valid @RequestBody VentasDTO ventasDTOList) {
        VentasDTO ventasGuardadas = ventasServices.save(ventasDTOList).getBody();
        if (ventasGuardadas != null) {
            return new ResponseEntity<>(ventasGuardadas, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/pageable")
    public ResponseEntity<Page<VentasDTO>> listarClientesPorPagina(
            @RequestParam("id") Long idUsuario,
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO) String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        ResponseEntity<Page<VentasDTO>> ventaDTO = ventasServices.listar(idUsuario, PageRequest.of(page, size, Sort.by(order)));
        return ventaDTO;
    }


    //generate controller for ventasDeliveryDTO
    @GetMapping("/delivery/{id}")
    public ResponseEntity<Object> consultaPedidoPorDelivery(@PathVariable("id") Long id) {
        return ventasServices.detalleVentasPorIdEmpleado(id);
    }

}
