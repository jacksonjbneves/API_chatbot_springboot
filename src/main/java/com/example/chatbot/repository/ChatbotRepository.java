package com.example.chatbot.repository;


import com.example.chatbot.entity.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {

    // Exemplo de busca por pergunta
    Chatbot findByPergunta(String pergunta);

}
