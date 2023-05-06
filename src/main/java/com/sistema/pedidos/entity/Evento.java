package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Evento")
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ID_Evento")
    private  long id;
    @Column(name = "EV_HoraIicio")
    private Time ev_horaInicio;
    @Column(name = "EV_HOraCierre")
    private Time ev_horaCierre;
}
