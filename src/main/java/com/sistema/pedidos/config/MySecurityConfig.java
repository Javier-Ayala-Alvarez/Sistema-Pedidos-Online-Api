package com.sistema.pedidos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sistema.pedidos.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {
		http
		.csrf()
		.disable()
		.cors()
		.disable()
		.authorizeRequests()
		.antMatchers("/generate-token","/usuarios/GuardarUsuarioCliente","/api/category/list","/api/product/list","/api/product/listCombo/{id}","/api/Evento/list").permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//.and()
		//.logout()
		//.logoutUrl("/logout")
		//.logoutSuccessUrl("");	
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/swagger-ui/**","/swagger-ui.html", "/v3/api-docs/**");
	}
	
	
}
