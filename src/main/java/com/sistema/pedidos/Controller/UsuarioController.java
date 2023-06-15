package com.sistema.pedidos.Controller;

import java.util.*;

import com.sistema.pedidos.DTO.RegistroDTO;
import com.sistema.pedidos.DTO.UsuarioClienteDTO;
import com.sistema.pedidos.DTO.UsuarioDTO;
import com.sistema.pedidos.entity.ClientesEntity;
import com.sistema.pedidos.entity.Rol;
import com.sistema.pedidos.repository.ClienteRepository;
import com.sistema.pedidos.repository.RolRepositorio;
import com.sistema.pedidos.repository.UsuarioRepositorio;
import com.sistema.pedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.UsuarioRol;
import com.sistema.pedidos.repository.UsuarioRepository;
import com.sistema.pedidos.service.UsuarioService;

import javax.validation.Valid;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*clientes*/
    @PostMapping("/")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> guardaUsuario(@RequestBody Usuario usuario) throws Exception {

        //usuario.setPerfil("default.png");
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> roles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        roles.add(usuarioRol);
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario, roles), HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/GuardarUsuarioEmpleado")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroDTO registroDTO) throws Exception {
        if (usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if (usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        //usuario.setUsername(registroDTO.getNombre());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        Rol rol;
        try {
            rol = rolRepositorio.findByNombre(registroDTO.getRol()).get();

        } catch (Exception e) {
            return new ResponseEntity<>("No existe el rol", HttpStatus.BAD_REQUEST);

        }
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        Set<UsuarioRol> roles = new HashSet<>();
        roles.add(usuarioRol);
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario, roles, registroDTO.getIdEmployee()), HttpStatus.CREATED);
    }

    @PutMapping("/ModificarUsuario")
    public ResponseEntity<?> modificar(@Valid @RequestBody UsuarioDTO usuario) {
        Rol rol = new Rol();
        try {
         rol = rolRepositorio.findByNombre(usuario.getRol().getNombre()).get();

        } catch (Exception e) {
            return new ResponseEntity<>("No existe el rol", HttpStatus.BAD_REQUEST);

        }
        usuario.setRol(rol);
        usuarioService.modificarUsuario(usuario);
        return new ResponseEntity<>("Guardado con exito", HttpStatus.OK);
    }

    @GetMapping("/todos")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    //@PreAuthorize("hasRole('ADNIN')")
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }


    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.eliminarUsuario(usuarioId);
    }


    @PostMapping ("/GuardarUsuarioCliente")
    public  ResponseEntity<?> guardarUsuarioCliente(@Valid @RequestBody UsuarioClienteDTO usurioCliente) throws Exception {
        if (usuarioRepositorio.existsByUsername(usurioCliente.getUsuario().getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if (usuarioRepositorio.existsByEmail(usurioCliente.getUsuario().getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usurioCliente.getUsuario().getUsername());
        usuario.setEmail(usurioCliente.getUsuario().getEmail());
        usuario.setPassword(passwordEncoder.encode(usurioCliente.getUsuario().getPassword()));
        Set<UsuarioRol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        roles.add(usuarioRol);
        ClientesEntity cliente = new ClientesEntity();
        cliente.setNombre(usurioCliente.getNombre());
        cliente.setApellido(usurioCliente.getApellido());
        cliente.setEstado(usurioCliente.getEstado());
        cliente.setUsuario(usuario);

        usuario.setCliente(cliente);


        return new ResponseEntity<>( usuarioService.guardarUsuario(usuario, roles),HttpStatus.OK);
    }

    @PutMapping("/ModificarUsuarioCliente")
    public  ResponseEntity<?> ModificarUsuarioCliente(@Valid @RequestBody UsuarioClienteDTO usurioCliente) throws Exception {


        if (!usurioCliente.isIdClienteValid() || !usurioCliente.getUsuario().isAllParamComplete()) return new ResponseEntity<>("Ingrese datos validos", HttpStatus.BAD_REQUEST);

        if (usuarioRepositorio.existsByUsername(usurioCliente.getUsuario().getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if (usuarioRepositorio.existsByEmail(usurioCliente.getUsuario().getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }


        Optional<Usuario> usuarioObj = usuarioRepository.findById(usurioCliente.getUsuario().getIdUsuario());

        if (!usuarioObj.isPresent()) return new ResponseEntity<>("No existe el usuario o el cliente", HttpStatus.BAD_REQUEST);

        Usuario usuario = usuarioObj.get();
        ClientesEntity cliente = usuarioObj.get().getCliente();

        cliente.setNombre(usurioCliente.getNombre());
        cliente.setApellido(usurioCliente.getApellido());
        cliente.setEstado(usurioCliente.getEstado());
        cliente.setIdCliente(usurioCliente.getIdCliente());

        usuario.setId(usurioCliente.getUsuario().getIdUsuario());
        usuario.setUsername(usurioCliente.getUsuario().getUsername());
        usuario.setEmail(usurioCliente.getUsuario().getEmail());
        usuario.setPassword(passwordEncoder.encode(usurioCliente.getUsuario().getPassword()));
        usuario.setEnabled(usurioCliente.getUsuario().getEstado());


        cliente.setUsuario(usuario);
        usuario.setCliente(cliente);

        return new ResponseEntity<>( usuarioService.guardarUsuario(usuario),HttpStatus.OK);
    }

}
