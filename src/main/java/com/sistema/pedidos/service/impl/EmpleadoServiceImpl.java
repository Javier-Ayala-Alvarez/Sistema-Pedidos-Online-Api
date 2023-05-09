package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.Empleado;
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

@AllArgsConstructor

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado,Integer> implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

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

}
