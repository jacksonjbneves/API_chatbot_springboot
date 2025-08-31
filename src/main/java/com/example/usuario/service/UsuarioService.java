package com.example.usuario.service;

import com.example.usuario.entity.Usuario;
import com.example.usuario.DTO.UsuarioDTO;
import com.example.usuario.repository.UsuarioRepository;
//import org.springframework.security.crypto.bcrypt.BCrypt;

import org.mindrot.jbcrypt.BCrypt;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioDTO salvar(Usuario usuario) {
        Usuario salvo = repository.save(usuario);
        return new UsuarioDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getNivelPermissao()
        );
    }

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getNivelPermissao()))
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Integer id) {
        return repository.findById(id)
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getNivelPermissao()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public String GerarCrypt(){
        String senhaCriptografada = BCrypt.hashpw("1290115", BCrypt.gensalt(10));
        //String senhaCriptografada = "senhanova";
        return senhaCriptografada;
    }


    public Usuario validarLogin(String email, String senha) throws Exception {
        Optional<Usuario> usuarioOpt = repository.findByEmail(email);

        if(usuarioOpt.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }

        //não deveria ser o DTO?
        Usuario usuario = usuarioOpt.get();

        // Verifica senha (se estiver usando bcrypt)
        if(!BCrypt.checkpw(senha, usuario.getSenha())) {
            throw new Exception("Senha inválida");
        }

        return usuario;
    }

    public String gerarToken(Usuario usuario) {
        String secret = "minhaChaveSecreta123JavaSpringBoot1290115"; // guarde em .env ou application.properties
        long expiration = 1000 * 60 * 60; // 1 hora => 1000 * 60 * 60

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("nivel", usuario.getNivelPermissao())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
