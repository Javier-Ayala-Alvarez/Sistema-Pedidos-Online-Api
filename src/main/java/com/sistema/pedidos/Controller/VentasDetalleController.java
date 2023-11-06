package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.PuestoDTO;
import com.sistema.pedidos.DTO.VentasDetalleDTO;
import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.service.VentasDetalleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/ventaDetalle")
@CrossOrigin("*")
public class VentasDetalleController {
    @Autowired
    VentasDetalleServices ventasDetalleServices;
    @PostMapping("/guardar")
    public ResponseEntity<ArrayList<VentasDetalleDTO>> guardar(@Valid @RequestBody ArrayList<VentasDetalleDTO> ventasDetalleDTOList) {

        ArrayList<VentasDetalleDTO> ventasDetalleGuardadoDTOList = (ArrayList<VentasDetalleDTO>) ventasDetalleServices.save(ventasDetalleDTOList).getBody();

        if (!ventasDetalleGuardadoDTOList.isEmpty()) {
            return new ResponseEntity<>(ventasDetalleGuardadoDTOList, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<VentasDetalleDTO>> listarPorId(@PathVariable Long id){
        try {
            System.out.println("hoal");
            List<VentasDetalleDTO> sucursalDTO= (List<VentasDetalleDTO>) ventasDetalleServices.buscarId(id).getBody();
            return new ResponseEntity<List<VentasDetalleDTO>>( sucursalDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<VentasDetalleDTO>>(HttpStatus.NOT_FOUND);
        }
    }

}
