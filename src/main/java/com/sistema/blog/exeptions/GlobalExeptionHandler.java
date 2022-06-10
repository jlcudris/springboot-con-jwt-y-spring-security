package com.sistema.blog.exeptions;

import com.sistema.blog.dto.ErrorDetalles;
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
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<ErrorDetalles>manejarResourceNotFoundExeption(
            ResourceNotFoundExeption exeption, WebRequest webRequest){

        return new ResponseEntity<>(
                new ErrorDetalles
                        (new Date(),exeption.getMessage(),webRequest.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppExeption.class)
    public ResponseEntity<ErrorDetalles>manejarBlogAppExeption(
            BlogAppExeption exeption, WebRequest webRequest){

        return new ResponseEntity<>(
                new ErrorDetalles
                        (new Date(),exeption.getMensaje(),webRequest.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles>manejarGlobalExeption(
            Exception exeption, WebRequest webRequest){

        return new ResponseEntity<>(
                new ErrorDetalles
                        (new Date(),exeption.getMessage(),webRequest.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> errores =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String nombreCampo =((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(nombreCampo,mensaje);
        });

        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);

    }
}
