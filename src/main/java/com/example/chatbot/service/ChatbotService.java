package com.example.chatbot.service;

import com.example.chatbot.DTO.ChatbotDTO;
import com.example.chatbot.entity.Chatbot;
import com.example.chatbot.repository.ChatbotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotService {

    private final ChatbotRepository repository;

    public ChatbotService(ChatbotRepository repository) {
        this.repository = repository;
    }

    //Lista todas as perguntas/respostas
    public List<Chatbot> getAllResponses() {
        return repository.findAll();
    }

    //Buscar pergunta/resposta
    public Chatbot getByPergunta(String pergunta) {
        return repository.findByPergunta(pergunta);
    }

    //Salvar pergunta/resposta
    public Chatbot savePerguntaResponse(String pergunta, String resposta) {
        if (repository.findByPergunta(pergunta) != null) {
            System.out.println("Existe pergunta");
            throw new IllegalArgumentException("Essa pergunta já existe!");
        }

        Chatbot pergresp = new Chatbot(pergunta,resposta);
        return repository.save(pergresp);
    }

    //Atualizar pergunta/resposta
    public Chatbot updatePerguntaResponse(Long id, String pergunta, String resposta) {
        Chatbot existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada"));

        existing.setPergunta(pergunta);
        existing.setResposta(resposta);

        return repository.save(existing);
    }

    //Deletar Pergunta/Resposta
    public void deletePergunta(Long id) {
        repository.deleteById(id);
    }

}
