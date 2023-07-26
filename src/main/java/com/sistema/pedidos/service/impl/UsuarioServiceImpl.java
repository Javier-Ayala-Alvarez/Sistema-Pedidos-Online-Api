package com.sistema.pedidos.service.impl;

import java.util.Optional;
import java.util.Set;

import com.sistema.pedidos.DTO.PuestoDTO;
import com.sistema.pedidos.DTO.UsuarioDTO;
import com.sistema.pedidos.entity.*;
import com.sistema.pedidos.exception.ResourceNotFoundException;
import com.sistema.pedidos.repository.EmpleadoRepository;
import com.sistema.pedidos.repository.UsuarioRolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sistema.pedidos.repository.RolRepository;
import com.sistema.pedidos.repository.UsuarioRepository;
import com.sistema.pedidos.service.UsuarioService;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    //@Autowired
    // private ModelMapper modelMapper;
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
            throw new Exception("el usuario ya existe");
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

    public ResponseEntity<Usuario> guardarUsuario(Usuario usuario) throws Exception {
        // TODO Auto-generated method stub

        Usuario usuarioLocal=  usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioLocal, HttpStatus.CREATED);

    }

    public Optional<Usuario> guardarUsuarioSinValidar(Usuario usuario, Set<UsuarioRol> usuarioRoles) {
        // TODO Auto-generated method stub
        Optional<Usuario> opt = Optional.of(usuarioRepository.save(usuario));
        return opt;
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

    @Transactional
    @Override
    public ResponseEntity<UsuarioDTO> modificarUsuario(UsuarioDTO usuario) {

        Usuario usuario1 = new Usuario();
        usuario1.setId(usuario.getId());
        usuario1.setUsername(usuario.getUsername());
        usuario1.setPassword(usuario.getPassword());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setEnabled(usuario.isEnabled());
        usuarioRepository.save(usuario1);
        UsuarioRol rolUsuario = usuarioRolesRepository.buscarRolUsuario(usuario.getId());
        rolUsuario.setRol(usuario.getRol());
        usuarioRolesRepository.save(rolUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


   /* private UsuarioDTO mapearDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    // Convierte de DTO a Entidad
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario; = modelMapper.map(usuarioDTO, Usuario.class);

        return usuario;
    }*/
}
