package com.example.usuario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(name = "nivel_permissao", nullable = false)
    private Byte nivelPermissao = 1;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Byte getNivelPermissao() { return nivelPermissao; }
    public void setNivelPermissao(Byte nivelPermissao) { this.nivelPermissao = nivelPermissao; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
