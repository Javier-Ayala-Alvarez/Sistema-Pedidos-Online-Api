package com.sistema.pedidos.Controller;

import com.sistema.pedidos.entity.Company;
import com.sistema.pedidos.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list/{id}")
    public Company listarCompany(@PathVariable Long id){
        return companyService.listarCompany(id);
    }
    @GetMapping("/list/todos")
    public List<Company> listarCompanyTodos(){
        return companyService.listarCompanyTodos();
    }

    @PutMapping("/update/{id}")
    public Company actualizarCompany(@RequestBody Company company, @PathVariable Long id){
        return companyService.actualizarCompany(company,id);
    }
}
