package com.example.usuario.DTO;

public class LoginResponseDTO {
    private int nivelPermissao;
    private String token;

    public LoginResponseDTO(int nivelPermissao, String token) {
        this.nivelPermissao = nivelPermissao;
        this.token = token;
    }

    public int getNivelPermissao() { return nivelPermissao; }
    public String getToken() { return token; }
}
