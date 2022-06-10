package com.sistema.blog.dto;

import java.util.Date;

public class ErrorDetalles {

    private Date marcaTiempo;
    private String mensaje;
    private String detalle;

    public ErrorDetalles(Date marcaTiempo, String mensaje, String detalle) {
        this.marcaTiempo = marcaTiempo;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public ErrorDetalles() {
    }

    public Date getMarcaTiempo() {
        return marcaTiempo;
    }

    public void setMarcaTiempo(Date marcaTiempo) {
        this.marcaTiempo = marcaTiempo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
