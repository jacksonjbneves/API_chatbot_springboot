package com.example.usuario.controller;

import com.example.usuario.DTO.LoginDTO;
import com.example.usuario.entity.Usuario;
import com.example.usuario.DTO.UsuarioDTO;
import com.example.usuario.DTO.LoginResponseDTO;
import com.example.usuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.salvar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Usuario usuario = service.validarLogin(loginDTO.getEmail(), loginDTO.getSenha());
            String token = service.gerarToken(usuario);

            return ResponseEntity.ok(new LoginResponseDTO(usuario.getNivelPermissao(), token));
        } catch (Exception e) {
            System.out.println("Erro");
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
    }

    @GetMapping("/bcrypt")
    public ResponseEntity<?> ShowBCrypt() { return ResponseEntity.ok(service.GerarCrypt()); }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
