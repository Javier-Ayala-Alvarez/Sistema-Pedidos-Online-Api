package com.sistema.pedidos.DTO;

import com.sistema.pedidos.entity.Company;
import lombok.Data;

import javax.persistence.*;

@Data
public class SucursalDTO {

    private Long id;

    private String nombre;
    private String abreviatura;
    private String direccion;
    private Double altitud;
    private Double longitud;

    private Double radio;
    private boolean estado=true;
}
