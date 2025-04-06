package com.Tarun.Quiz.controller;

import com.Tarun.Quiz.dto.LoginRequest;
import com.Tarun.Quiz.entity.QuizQuestion;
import com.Tarun.Quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuestionService questionService;

    // Hardcoded credentials for now
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        if (USERNAME.equals(loginRequest.getUsername()) && PASSWORD.equals(loginRequest.getPassword())) {
            response.put("message", "Login Successful!");
            response.put("status", "success");
        } else {
            response.put("message", "Invalid username or password");
            response.put("status", "error");
        }

        return response;
    }

    @GetMapping("/questions")
    public List<QuizQuestion> getQuestions() {
        List<QuizQuestion> questions = questionService.getAllQuestions();
        return questions;
    }


    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public QuizQuestion saveQuestion(@RequestBody QuizQuestion question) {
        return questionService.saveQuestion(question);

    }
}
