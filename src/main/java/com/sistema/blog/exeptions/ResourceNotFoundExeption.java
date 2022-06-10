package com.sistema.blog.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundExeption extends RuntimeException {

    public ResourceNotFoundExeption(String nombreDelRecurso,String nombreDelCampo,long valorDelCampo){
        super(String.format("%s No encontrado con : %s : '%s'",nombreDelRecurso,nombreDelCampo,valorDelCampo));
        this.nombreDelRecurso=nombreDelRecurso;
        this.nombreDelCampo=nombreDelCampo;
        this.valorDelCampo=valorDelCampo;

    }

    private String nombreDelRecurso;
    private String nombreDelCampo;
    private long valorDelCampo;


    public String getNombreDelRecurso() {
        return nombreDelRecurso;
    }

    public void setNombreDelRecurso(String nombreDelRecurso) {
        this.nombreDelRecurso = nombreDelRecurso;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }

    public long getValorDelCampo() {
        return valorDelCampo;
    }

    public void setValorDelCampo(long valorDelCampo) {
        this.valorDelCampo = valorDelCampo;
    }
}
