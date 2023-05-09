package com.sistema.pedidos.exception;

import org.springframework.http.HttpStatus;
/**
 * Excepción personalizada para la aplicación de Blog. Contiene un estado HTTP y un mensaje descriptivo.
 */
public class BlogAppException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private HttpStatus estado;
    private String mensaje;
    /**
     * Crea una nueva instancia de BlogAppException con el estado HTTP y mensaje especificados.
     *
     * @param estado El estado HTTP asociado con la excepción.
     * @param mensaje Un mensaje descriptivo que explica la excepción.
     */
    public BlogAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
