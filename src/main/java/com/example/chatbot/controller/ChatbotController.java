package com.example.chatbot.controller;

import com.example.chatbot.DTO.ChatbotDTO;
import com.example.chatbot.entity.Chatbot;
import com.example.chatbot.service.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questchatbot")
public class ChatbotController {
    private final ChatbotService service;

    public ChatbotController(ChatbotService service) {
        this.service = service;
    }

    // GET Lista perguntas/respostas
    @GetMapping
    public List<Chatbot> getAll() {
        //System.out.println("aqui");
        return service.getAllResponses();
    }

    @PostMapping("/pergunta")
    public Chatbot getResposta(@RequestBody ChatbotDTO request) {
        return service.getByPergunta(request.getPergunta());
    }

    // POST (salva pergunta/resposta)
    @PostMapping("/pergresp")
    public ResponseEntity<?> getPergResp(@RequestBody ChatbotDTO request) {
        try {
            Chatbot saved = service.savePerguntaResponse(request.getPergunta(), request.getResposta());
            return ResponseEntity.ok(saved);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    //POST Atualizar pergunta/resposta
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerguntaResposta(@PathVariable Long id, @RequestBody ChatbotDTO request) {
        try {
            Chatbot updated = service.updatePerguntaResponse(id, request.getPergunta(), request.getResposta());
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    //DELETE Deletar pergunta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePergunta(@PathVariable Long id) {
        try {
            service.deletePergunta(id);
            return ResponseEntity.ok(Map.of("message", "Pergunta deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao deletar pergunta"));
        }
    }

}
