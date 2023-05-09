package com.sistema.pedidos.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PuestoRespuesta {
    private List<PuestoDTO> contenido;
    private int numPagina;
    private int medidapagina;
    private Long totalElementos;
    private int totalPaginas;
    private boolean ultima;
}
