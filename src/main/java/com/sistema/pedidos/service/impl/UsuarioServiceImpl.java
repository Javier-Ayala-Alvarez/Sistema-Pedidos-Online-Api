package com.sistema.pedidos.service.impl;

import java.util.Optional;
import java.util.Set;

import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.exception.ResourceNotFoundException;
import com.sistema.pedidos.repository.EmpleadoRepository;
import com.sistema.pedidos.repository.UsuarioRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.UsuarioRol;
import com.sistema.pedidos.repository.RolRepository;
import com.sistema.pedidos.repository.UsuarioRepository;
import com.sistema.pedidos.service.UsuarioService;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRolesRepository usuarioRolesRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Transactional
    @Override
    public ResponseEntity<Usuario> guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles, Integer idEmployee) throws Exception {
        // TODO Auto-generated method stub
        Empleado empleadoExistente = empleadoRepository.findById(idEmployee).orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", idEmployee));

        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioLocal != null) {
            throw new Exception("el usario ya existe");
        } else {
            usuarioLocal = usuarioRepository.save(usuario);
            empleadoExistente.setUsuario(usuarioLocal);
            empleadoRepository.save(empleadoExistente);
            for (UsuarioRol usuarioRol : usuarioRoles) {
                usuarioRol.setUsuario(usuarioLocal);
                usuarioRolesRepository.save(usuarioRol);
            }
        }
        return new ResponseEntity<>(usuarioLocal, HttpStatus.CREATED);

    }
    @Override
    public ResponseEntity<Usuario> guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        // TODO Auto-generated method stub

        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioLocal != null) {
            throw new Exception("el usario ya existe");
        } else {
            usuarioLocal = usuarioRepository.save(usuario);
            for (UsuarioRol usuarioRol : usuarioRoles) {
                usuarioRol.setUsuario(usuarioLocal);
                usuarioRolesRepository.save(usuarioRol);
            }
        }
        return new ResponseEntity<>(usuarioLocal, HttpStatus.CREATED);

    }

    @Override
    public Usuario obtenerUsuario(String username) {
        // TODO Auto-generated method stub
        return usuarioRepository.findByUsername(username);
    }


    @Override
    public void eliminarUsuario(Long usuarioId) {
        // TODO Auto-generated method stub
        usuarioRepository.deleteById(usuarioId);
    }


}
