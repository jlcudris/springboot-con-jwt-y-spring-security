package com.sistema.blog.Security;

public class JWTAuthResponseDto {
    private String tokenAcceso;
    private String tipoToken ="Bearer";

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public JWTAuthResponseDto(String tokenAcceso, String tipoToken) {
        this.tokenAcceso = tokenAcceso;
        this.tipoToken = tipoToken;
    }

    public JWTAuthResponseDto(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }
}
