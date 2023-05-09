package com.sistema.pedidos.service;

import com.sistema.pedidos.commons.GenericServiceApi;
import com.sistema.pedidos.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EmpleadoService extends GenericServiceApi<Empleado,Integer> {
    Page<Empleado> getAllWithPaginationByEstado(Pageable pageables,Boolean estado);

    ResponseEntity darDeBajaEmpleado(Integer id);

    Page<Empleado>listarEmpleadoPorNombrePagina(String CT_Nombre, Pageable pageable);

}
