package com.sistema.pedidos.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistroDTO {
	@NotNull(message = "Debe selecionar el cliente")
	private Integer idEmployee;
	@NotEmpty(message = "El usuario de puede ser vacia")
	@Size(min = 4,message = "El usuario debe tener al menos 4 caracteres")

	//private String nombre;
	private String username;
	@NotEmpty(message = "La contraseña de puede ser vacia")
	@Email(message = "Debe ser de tipo correo")
	private String email;
	@NotEmpty(message = "La contraseña de puede ser vacia")
	@Size(min = 4,message = "La contraseña debe tener al menos 4 caracteres")

	private String password;
	private String rol;

}
