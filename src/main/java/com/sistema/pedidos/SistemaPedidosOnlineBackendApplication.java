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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

//		Usuario usuario = new Usuario();


//		usuario.setUsername("admin");
//		usuario.setPassword(bCryptPasswordEncoder.encode("12345"));

//		usuario.setEmail("nelson.karma2012@gmail.com");

//		Rol rol = new Rol();
//		rol.setRolId(1L);
//		rol.setNombre("ADMIN");

//		Set<UsuarioRol> usuarioRoles = new HashSet<>();
//		UsuarioRol usuarioRol = new UsuarioRol();
//		usuarioRol.setRol(rol);
//		usuarioRol.setUsuario(usuario);
//		usuarioRoles.add(usuarioRol);
//		Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles).getBody();
//		System.out.println(usuarioGuardado.getUsername());
//
//
//
//
//		Usuario usuario2 = new Usuario();
//
//
//		usuario2.setUsername("cliente");
//		usuario2.setPassword(bCryptPasswordEncoder.encode("123456"));
//
//		usuario2.setEmail("mauricoi_cc@gmail.com");
//
//		Rol rol2 = new Rol();
//		rol2.setRolId(2L);
//		rol2.setNombre("CLIENTE");
//
//		Set<UsuarioRol> usuarioRoles2 = new HashSet<>();
//		UsuarioRol usuarioRol2 = new UsuarioRol();
//		usuarioRol2.setRol(rol2);
//		usuarioRol2.setUsuario(usuario2);
//		usuarioRoles2.add(usuarioRol2);
//
//		 usuarioGuardado = usuarioService.guardarUsuario(usuario2, usuarioRoles2).getBody();
//		System.out.println(usuarioGuardado.getUsername());
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

	/*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }*/
}
