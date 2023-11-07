package com.sistema.pedidos.DTO;

import com.sistema.pedidos.entity.PuestoLaboral;
import com.sistema.pedidos.entity.Sucursal;
import com.sistema.pedidos.entity.Usuario;
import com.sistema.pedidos.entity.VentaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Data
public class EmpleadoDTO {


    private Integer id;
    private String nombre;
    private String apellido;

    private Date fechaNacimiento;
    private Integer telefono;

    private String lugarNacimiento;
    private Double salario;
    private String dui;
    private Boolean estado;

    String estadoEmpleado;

    private SucursalDTO sucursal;
    private PuestoDTO puestoLaboral;
    private UsuarioDTO usuario;


}
