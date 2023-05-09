package com.sistema.pedidos.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetalles {
    private Date marcaTiempo;
    private String mensaje;
    private String detalle;
    public ErrorDetalles(Date marcaDeTiempo, String mensaje, String detalles) {
        super();
        this.marcaTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalle = detalles;
    }

}
