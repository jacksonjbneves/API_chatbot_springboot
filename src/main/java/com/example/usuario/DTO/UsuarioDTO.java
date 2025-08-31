package com.example.usuario.DTO;

public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String email;
    private Byte nivelPermissao;

    // construtores
    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String nome, String email, Byte nivelPermissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nivelPermissao = nivelPermissao;
    }

    // getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Byte getNivelPermissao() { return nivelPermissao; }
    public void setNivelPermissao(Byte nivelPermissao) { this.nivelPermissao = nivelPermissao; }
}
