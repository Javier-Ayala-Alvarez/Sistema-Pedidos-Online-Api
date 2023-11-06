package com.sistema.pedidos.entity;

import com.sistema.pedidos.Utileria.EstadoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
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

    @Column
    String estadoEmpleado;
    @ManyToOne
    private Sucursal sucursal;
    @ManyToOne
    private PuestoLaboral puestoLaboral;
    @OneToOne
    private Usuario usuario;

    @ManyToMany(mappedBy = "empleados")
    Set<VentaEntity> ventas;
}
