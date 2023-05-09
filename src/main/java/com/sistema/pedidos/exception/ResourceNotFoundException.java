package com.sistema.pedidos.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción personalizada para indicar que un recurso no ha sido encontrado.
 */
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String nombreRecurso;
    private String nombreCampo;
    private Integer valorCampo;
    /**
     * Crea una nueva instancia de ResourceNotFoundException con el nombre del recurso, el nombre del campo y el valor del campo especificados.
     *
     * @param nombreRecurso El nombre del recurso que no se encontró.
     * @param nombreCampo El nombre del campo que se buscó.
     * @param valorCampo El valor del campo que se buscó.
     */
    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, Integer valorCampo) {
        super(String.format("%s no encontrada con : %s : '%s' ", nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }

}
