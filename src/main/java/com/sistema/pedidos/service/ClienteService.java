package com.sistema.pedidos.service;


import com.sistema.pedidos.DTO.ClienteUsuarioDTO;
import com.sistema.pedidos.commons.GenericServiceApi;
import com.sistema.pedidos.entity.ClientesEntity;
import org.springframework.http.ResponseEntity;

public interface ClienteService extends GenericServiceApi<ClientesEntity, Integer> {



    ResponseEntity<ClienteUsuarioDTO> buscarClientePorIdUsuario(Long idUsuario);

}
