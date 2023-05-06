package com.sistema.pedidos.Controller;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.pedidos.entity.Rol;
import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.UsuarioRol;
import com.sistema.pedidos.repository.UsuarioRepository;
import com.sistema.pedidos.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public Usuario guardaUsuario(@RequestBody Usuario usuario) throws Exception{
		
		usuario.setPerfil("default.png");
		usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
		
		Set<UsuarioRol> roles = new HashSet<>();
		
		Rol rol = new Rol();
		rol.setRolId(2L);
		rol.setNombre("NORMAL");
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol);
		
		roles.add(usuarioRol);
		return usuarioService.guardarUsuario(usuario, roles);
	}
	
	@GetMapping("/todos")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public List<Usuario> listarUsuarios(){
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
	
	
	
	
	
}
