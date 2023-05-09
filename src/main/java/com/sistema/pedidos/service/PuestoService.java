package com.sistema.pedidos.service;

import com.sistema.pedidos.DTO.PuestoDTO;
import com.sistema.pedidos.DTO.PuestoRespuesta;
import org.springframework.http.ResponseEntity;

public interface PuestoService {
    public ResponseEntity<PuestoRespuesta> mostrarTodos(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    public PuestoDTO mostrarId(Integer id);
    public PuestoDTO guardar(PuestoDTO sucursal);
    public PuestoDTO editar(PuestoDTO sucursal, Integer id);
    public void eliminar(Integer id);

}
