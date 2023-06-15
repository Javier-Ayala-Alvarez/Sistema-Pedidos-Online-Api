package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.ClienteUsuarioDTO;
import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.ClientesEntity;
import com.sistema.pedidos.repository.ClienteRepository;
import com.sistema.pedidos.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class ClienteServiceImpl extends GenericServiceImpl<ClientesEntity, Integer> implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public JpaRepository<ClientesEntity, Integer> getDao() {
        return clienteRepository;
    }

    @Override
    public ResponseEntity<ClienteUsuarioDTO> buscarClientePorIdUsuario(Long idUsuario) {
        Optional<ClientesEntity> cliente = Optional.ofNullable(clienteRepository.buscarClientePorIdUsuario(idUsuario));

        if(!cliente.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        ClienteUsuarioDTO clienteUsuarioDTO = new ClienteUsuarioDTO();
        ClientesEntity clienteObj = cliente.get();
        clienteUsuarioDTO.setIdCliente(clienteObj.getIdCliente());
        clienteUsuarioDTO.setNombre(clienteObj.getNombre());
        clienteUsuarioDTO.setApellido(clienteObj.getApellido());
        clienteUsuarioDTO.setCorreo(clienteObj.getUsuario().getEmail());
        clienteUsuarioDTO.setUsuario(clienteObj.getUsuario().getUsername());

        return ResponseEntity.ok(clienteUsuarioDTO);

    }
}
