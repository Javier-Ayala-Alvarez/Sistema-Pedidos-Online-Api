package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.ResportVentaDTO;
import com.sistema.pedidos.DTO.TextoDTO;
import com.sistema.pedidos.DTO.VentasDTO;
import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.service.VentasServices;
import com.sistema.pedidos.service.impl.VentasServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("venta")
@CrossOrigin("*")
public class VentasController {
    @Autowired
    VentasServices ventasServices;

    @Autowired
    VentasServicesImpl ventasServicesImpl;

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

    // cambiar estado del pedido
    @PutMapping("/cambiarEstado")
    public ResponseEntity<Object> cambiarEstadoPedido(@RequestBody TextoDTO texto) {
        return ventasServices.cambiarEstadoPedido(texto.getId(), texto.getTexto());
    }

    // agregar comentario al pedido
    @PutMapping("/agregarComentario")
    public ResponseEntity<Object> agregarComentarioPedido(@RequestBody TextoDTO texto) {
        return ventasServices.agregarComentarioPedido(texto.getId(), texto.getTexto());
    }

    // Controlador que devuelve todas los deliverys de un usuario
    @GetMapping("/deliverys/{id}")
    public ResponseEntity<Object> obtenerEntregasPorIdEmpleado(@PathVariable("id") Long id) {
        return ventasServices.obtenerEntregasPorIdEmpleado(id);
    }

    // Controlador que devuelve el detalle de un pedido
    @GetMapping("/pedido/detalle/{id}")
    public ResponseEntity<Object> detalleDePedidoPorIdDeVenta(@PathVariable("id") Long id) {
        return ventasServices.detalleDePedidoPorIdDeVenta(id);
    }


    @GetMapping("/listarReporteVentas/{fecha}")
    public ResponseEntity<Object> listarReporteVentas(@PathVariable("fecha") String fecha){
        return new ResponseEntity<>(ventasServices.listarReporteVentas(fecha), HttpStatus.OK);
    }

}
