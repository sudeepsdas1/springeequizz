package com.sudeep.quizee.controller;

import com.sudeep.quizee.model.Question;
import com.sudeep.quizee.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;


@RestController
@RequestMapping("question")
public class questionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

            return questionService.getAllQuestions();

    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);

    }

    @PostMapping("add")
    public ResponseEntity<String>addQuestion(@RequestBody Question question){

        return questionService.addQuestion(question);
    }


}
