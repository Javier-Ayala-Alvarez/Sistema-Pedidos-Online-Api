package com.sistema.pedidos.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistema.pedidos.entity.Rol;
import com.sistema.pedidos.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioRolDTO {
    @JsonProperty("usuarioRolId")
    private Long usuarioRolId;
    @JsonProperty("usuario")
    private Usuario usuario;
    @JsonProperty("rol")
    private Rol rol;

  
}
