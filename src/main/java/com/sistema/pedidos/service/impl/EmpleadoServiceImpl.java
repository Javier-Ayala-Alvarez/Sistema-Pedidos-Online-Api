package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.entity.Sucursal;
import com.sistema.pedidos.repository.BranchOfficeRepository;
import com.sistema.pedidos.repository.EmpleadoRepository;
import com.sistema.pedidos.service.EmpleadoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Optional;

@AllArgsConstructor

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado,Integer> implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final BranchOfficeRepository branchOfficeRepository;

    @Override
    public JpaRepository<Empleado, Integer> getDao() {
        return empleadoRepository;
    }


    @Override
    public Page<Empleado> getAllWithPaginationByEstado(Pageable pageables, Boolean estado) {

        return empleadoRepository.findByEstadoEquals(pageables,estado);
    }


    public ResponseEntity darDeBajaEmpleado(Integer idEmpleado) {
        try {
            empleadoRepository.darDeBajaEmpleado(idEmpleado);
        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Empleado> listarEmpleadoPorNombrePagina(String CT_Nombre, Pageable pageable) {
        return empleadoRepository.listarEmpleadoPorNombrePagina(CT_Nombre,pageable);
    }

    @Override
    public Empleado listarEmpleadoPorId(Integer id) {
        return empleadoRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<Empleado> actualizarEmpleado2(Empleado empleado, Integer id) {
        Optional<Empleado> empleadoOptional=empleadoRepository.findById(id);
        Optional<Sucursal> sucursalOptional=branchOfficeRepository.findById(empleado.getSucursal().getId());
        if (!empleadoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!sucursalOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        empleado.setId(empleadoOptional.get().getId());
        empleado.setSucursal(sucursalOptional.get());


        Empleado emmpleadoGuardado=empleadoRepository.save(empleado);
        return  ResponseEntity.ok(emmpleadoGuardado);
    }

    @Override
    public Optional<String> getEstadoEmpleadoByIdUsuario(Long id) {
        Optional<String> estadoEmpleado=Optional.ofNullable(empleadoRepository.getEstadoEmpleadoByIdUsuario(id));
        return estadoEmpleado;

    }


    public ResponseEntity updateEstadoEmpleado(Long id, String estado) {
        int rpta=empleadoRepository.updateEstadoEmpleado(estado,id);
        if (rpta>0){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }

}
