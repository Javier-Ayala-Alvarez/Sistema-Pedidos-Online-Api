package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.ClientesEntity;
import com.sistema.pedidos.repository.ClienteRepository;
import com.sistema.pedidos.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ClienteServiceImpl extends GenericServiceImpl<ClientesEntity, Integer> implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public JpaRepository<ClientesEntity, Integer> getDao() {
        return clienteRepository;
    }
}
