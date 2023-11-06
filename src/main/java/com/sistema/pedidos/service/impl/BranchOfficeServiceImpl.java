package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.entity.Company;
import com.sistema.pedidos.entity.Sucursal;
import com.sistema.pedidos.repository.BranchOfficeRepository;
import com.sistema.pedidos.repository.CompanyRepository;
import com.sistema.pedidos.service.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchOfficeServiceImpl implements BranchOfficeService {

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public ResponseEntity<Object> guardarSucursal(Sucursal sucursal) {

        Optional<Company> companyOptional = companyRepository.findById(sucursal.getEmpresa().getId());
        if (!companyOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        sucursal.setNombre(sucursal.getNombre());
        sucursal.setAbreviatura(sucursal.getAbreviatura());
        sucursal.setDireccion(sucursal.getDireccion());
        sucursal.setEstado(sucursal.isEstado());
        sucursal.setEmpresa(companyOptional.get());

        Sucursal sucursalGuardado = branchOfficeRepository.save(sucursal);
        return ResponseEntity.ok(sucursalGuardado);

    }

    @Override
    public ResponseEntity<Sucursal> actualizarSucursal(Sucursal sucursal, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(sucursal.getEmpresa().getId());
        Optional<Sucursal> sucursalOptional = branchOfficeRepository.findById(id);

        if (!companyOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!sucursalOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        sucursal.setNombre(sucursal.getNombre());
        sucursal.setAbreviatura(sucursal.getAbreviatura());
        sucursal.setDireccion(sucursal.getDireccion());
        sucursal.setEstado(sucursal.isEstado());

        sucursal.setEmpresa(companyOptional.get());
        sucursal.setId(sucursalOptional.get().getId());


        Sucursal sucursalGuardado = branchOfficeRepository.save(sucursal);
        return ResponseEntity.ok(sucursalGuardado);
    }

    @Override
    public List<Sucursal> listarSucursalActivo() {
        return new ArrayList<>(branchOfficeRepository.listarSucursalActivo());
    }

    @Override
    public Sucursal listarSucursalPorId(Long id) {
        return branchOfficeRepository.findById(id).get();
    }

    @Override
    public List<Sucursal> listarSucursal() {
        return branchOfficeRepository.findAll();
    }


    @Override
    public Page<Sucursal> listarSucursalPorPagina(Pageable pageable) {
        return branchOfficeRepository.findAll(pageable);
    }

    @Override
    public Page<Sucursal> listarSucursalPorNombrePagina(String nombre, Pageable pageable) {
        return branchOfficeRepository.listarSucursalPorNombrePagina(nombre, pageable);
    }

    @Override
    public void eliminar(long id) {
        branchOfficeRepository.deleteById(id);
    }

    @Override
    public Sucursal getsucursalByIdUsuario(Long id) {
        return branchOfficeRepository.getsucursalByIdUsuario(id);
    }
}
