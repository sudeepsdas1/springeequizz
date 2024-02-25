package com.sudeep.quizee.controller;

import com.sudeep.quizee.model.QuestionWrapper;
import com.sudeep.quizee.service.QuizService;
import com.sudeep.quizee.model.Question;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("quiz")
public class quizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){

return quizService.createQuiz(category,numQ,title);
    }
    @GetMapping("get/{id")
    public ResponseEntity<List<QuestionWrapper  >> getQuizQuestion(@PathVariable Integer id){
    quizService.getQuizQuestion(id);
    }
}