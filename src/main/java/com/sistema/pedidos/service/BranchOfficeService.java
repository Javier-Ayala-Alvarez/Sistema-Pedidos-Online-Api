package com.sistema.pedidos.service;

import com.sistema.pedidos.entity.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;

import java.util.List;


public interface BranchOfficeService {

    public ResponseEntity<Object> guardarSucursal(Sucursal sucursal);

    public ResponseEntity<Sucursal> actualizarSucursal(Sucursal sucursal,Long id);

    public List<Sucursal> listarSucursalActivo();

    public Sucursal listarSucursalPorId(Long id);


    public Page<Sucursal> listarSucursalPorPagina(Pageable pageable);

    public Page<Sucursal> listarSucursalPorNombrePagina(String nombre, Pageable pageable);

    public void eliminar(long id);
}
