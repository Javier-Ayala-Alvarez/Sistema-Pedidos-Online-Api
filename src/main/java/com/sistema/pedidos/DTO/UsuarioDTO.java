package com.sistema.pedidos.DTO;

import com.sistema.pedidos.entity.Rol;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data

public class UsuarioDTO {
    @NotNull(message = "El id no debe ir vacio!!")
    private Long id;
    @NotEmpty(message = "Debe ingresar el username!!")
    private String username;
    @NotEmpty(message = "La contraseña de puede ser vacia!!")
    @Size(min = 4,message = "La contraseña debe tener al menos 4 caracteres!!")
    private String password;

    @NotEmpty(message = "El email no puede ser vacia!!")
    @Email(message = "Debe ser de tipo correo!!")
    private String email;


    private Rol rol;
    private boolean enabled =true;
}
