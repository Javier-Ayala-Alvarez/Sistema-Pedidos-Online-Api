package com.sistema.pedidos.service;

import com.sistema.pedidos.commons.GenericServiceApi;
import com.sistema.pedidos.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface EmpleadoService extends GenericServiceApi<Empleado,Integer> {
    Page<Empleado> getAllWithPaginationByEstado(Pageable pageables,Boolean estado);

    ResponseEntity darDeBajaEmpleado(Integer id);

    Page<Empleado>listarEmpleadoPorNombrePagina(String CT_Nombre, Pageable pageable);

    Empleado listarEmpleadoPorId(Integer id);

    ResponseEntity<Empleado> actualizarEmpleado2(Empleado empleado,Integer id);


    // obtener estado de empleado por id
    public Optional<String> getEstadoEmpleadoByIdUsuario(Long id);


    public ResponseEntity updateEstadoEmpleado(Long id, String estado);




}
