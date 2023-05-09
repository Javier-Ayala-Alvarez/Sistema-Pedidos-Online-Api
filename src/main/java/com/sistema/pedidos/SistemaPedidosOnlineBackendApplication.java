package com.sistema.pedidos;

import com.sistema.pedidos.entity.Rol;
import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.UsuarioRol;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sistema.pedidos.service.UsuarioService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SistemaPedidosOnlineBackendApplication implements CommandLineRunner{
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosOnlineBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Usuario usuario = new Usuario();
	
		
		usuario.setUsername("nelson");
		usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
		usuario.setNombre("NELSON");
		usuario.setApellido("ENRIQUE");
		usuario.setEmail("nelson.karma2012@gmail.com");
		usuario.setPerfil("foto.png");
		usuario.setTelefono("7431-6963");
		
		Rol rol = new Rol();
		rol.setRolId(1L);
		rol.setNombre("ADMIN");
		
		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRol(rol);
		usuarioRol.setUsuario(usuario);
		usuarioRoles.add(usuarioRol);
		
		Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
		System.out.println(usuarioGuardado.getUsername());*/




		/*Usuario usuario2 = new Usuario();
	
		
		usuario2.setUsername("mauricio");
		usuario2.setPassword(bCryptPasswordEncoder.encode("123456"));
		usuario2.setNombre("MAURICIO");
		usuario2.setApellido("MARTINEZ");
		usuario2.setEmail("mauricoi_cc@gmail.com");
		usuario2.setPerfil("foto2.png");
		usuario2.setTelefono("7451-6993");
		
		Rol rol2 = new Rol();
		rol2.setRolId(2L);
		rol2.setNombre("NORMAL");
		
		Set<UsuarioRol> usuarioRoles2 = new HashSet<>();
		UsuarioRol usuarioRol2 = new UsuarioRol();
		usuarioRol2.setRol(rol2);
		usuarioRol2.setUsuario(usuario2);
		usuarioRoles2.add(usuarioRol2);
		
		Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario2, usuarioRoles2);
		System.out.println(usuarioGuardado.getUsername());*/
	}
	/*@Bean
	public WebMvcConfigurer crosConfigurer() {
		return new WebMvcConfigurer() {
			@Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("*").allowedHeaders("*");
            }
        };
	}*/

	/*Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }*/
}
