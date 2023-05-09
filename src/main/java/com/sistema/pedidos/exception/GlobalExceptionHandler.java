package com.sistema.pedidos.exception;

import com.sistema.pedidos.DTO.ErrorDetalles;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
   /**
    * Manejador de excepciones para la clase ConfigDataResourceNotFoundException.
    *
    * @param exception La excepción que se está manejando.
    * @param webRequest La solicitud web que generó la excepción.
    * @return Una ResponseEntity que contiene los detalles del error y un estado HTTP de NO ENCONTRADO.
    */
   @ExceptionHandler(ConfigDataResourceNotFoundException.class)
   public ResponseEntity<ErrorDetalles> resourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
      ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
      return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);

   }

   @ExceptionHandler(BlogAppException.class)
   public ResponseEntity<ErrorDetalles> manejarBlogAppException(BlogAppException exception,WebRequest webRequest){
      ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
      return new ResponseEntity<>(errorDetalles,HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception,WebRequest webRequest){
      ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
      return new ResponseEntity<>(errorDetalles,HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {
      Map<String, String> errores = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String nombreCampo = ((FieldError)error).getField();
         String mensaje = error.getDefaultMessage();

         errores.put(nombreCampo, mensaje);
      });

      return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
   }
}
