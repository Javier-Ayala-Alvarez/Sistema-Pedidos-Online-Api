package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.entity.Company;
import com.sistema.pedidos.repository.CompanyRepository;
import com.sistema.pedidos.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Company actualizarCompany(Company company, Long id) {
        Company existenteCompany=companyRepository.findById(id).get();

        try{
            existenteCompany.setCp_Nombre(company.getCp_Nombre());
            existenteCompany.setCp_Direccion(company.getCp_Direccion());
            existenteCompany.setCp_abreviatura(company.getCp_abreviatura());
            Company companyGuardado=companyRepository.save(existenteCompany);
            return companyGuardado;
        }catch (Exception e){
            return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST).getBody();
        }
    }

    @Override
    public Company listarCompany(Long id) {
        return  companyRepository.findById(id).get() ;
    }

    @Override
    public List<Company> listarCompanyTodos() {
        return companyRepository.findAll();
    }
}
