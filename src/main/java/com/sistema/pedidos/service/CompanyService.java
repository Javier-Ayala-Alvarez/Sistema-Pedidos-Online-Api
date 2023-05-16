package com.sistema.pedidos.service;

import com.sistema.pedidos.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    public Company actualizarCompany(Company company,Long id);

    public Company listarCompany(Long id);

    public List<Company> listarCompanyTodos();
}
