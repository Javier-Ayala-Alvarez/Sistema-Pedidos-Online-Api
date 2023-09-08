package com.sistema.pedidos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuestoDTO {
    private Integer id;
    @NotEmpty
    @Size(min = 5,message = "El nombre del puesto deberia tener al menos 5 caracteres")
    private String nombre;
    private Boolean estado;
}
