package com.sistema.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer idEmpleado;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Date fechaNacimiento;
    @Column
    private Integer telefono;
    @Column
    private  String lugarNacimiento;
    @Column
    private Double salario;
    @Column
    private String dui;
    @Column
    private Boolean estado;
    @ManyToOne
    private Sucursal sucursal;
    @ManyToOne
    private PuestoLaboral puestoLaboral;
    @ManyToOne
    private Usuario usuario;
}
