package com.sistema.pedidos.Controller;

import java.util.*;

import com.sistema.pedidos.DTO.ClienteUsuarioDTO;
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
    private ClienteService clienteService;

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
        rol.setNombre("CLIENTE");

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


    @PostMapping("/GuardarUsuarioCliente")
    public ResponseEntity<?> guardarUsuarioCliente(@Valid @RequestBody UsuarioClienteDTO usuarioCliente) throws Exception {
        if (usuarioRepositorio.existsByUsername(usuarioCliente.getUsuario().getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if (usuarioRepositorio.existsByEmail(usuarioCliente.getUsuario().getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioCliente.getUsuario().getUsername());
        usuario.setEmail(usuarioCliente.getUsuario().getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioCliente.getUsuario().getPassword()));

        Set<UsuarioRol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("CLIENTE");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        roles.add(usuarioRol);
        ClientesEntity cliente = new ClientesEntity();
        cliente.setNombre(usuarioCliente.getNombre());
        cliente.setApellido(usuarioCliente.getApellido());
        cliente.setEstado(usuarioCliente.getEstado());
        cliente.setUsuario(usuario);
        cliente.setApodo(usuarioCliente.getApodo());
        cliente.setFechaNacimiento(usuarioCliente.getFechaNacimiento());

        usuario.setCliente(cliente);

        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario, roles), HttpStatus.OK);
    }

    @PutMapping("/ModificarUsuario/{idUsuario}")
    public ResponseEntity<?> ModificarUsuarioCliente(@Valid @RequestBody
                                                     ClienteUsuarioDTO usurioCliente,
                                                     @PathVariable Long idUsuario) throws Exception {

        if (!usurioCliente.isParamComplete() || idUsuario == null )
            return new ResponseEntity<>("Ingrese datos validos", HttpStatus.BAD_REQUEST);

        Optional<ClientesEntity> clienteObj = Optional.ofNullable(clienteService.get(usurioCliente.getIdCliente()));
        if (!clienteObj.isPresent())
            return new ResponseEntity<>("No existe el usuario o el cliente", HttpStatus.BAD_REQUEST);
        ClientesEntity cliente = clienteObj.get();
        Usuario usuario = clienteObj.get().getUsuario();

        cliente.setNombre(usurioCliente.getNombre());
        cliente.setApellido(usurioCliente.getApellido());
        usuario.setUsername(usurioCliente.getUsuario());
        usuario.setEmail(usurioCliente.getCorreo());
        usuario.setPassword(passwordEncoder.encode(usurioCliente.getPassword()));

        cliente.setUsuario(usuario);
        usuario.setCliente(cliente);

        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), HttpStatus.OK);
    }

}
