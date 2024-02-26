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
// all the package imports for all the annottation used


@RestController//to create restful web servicess
@RequestMapping("question")////request mapping to (base url) to hit for any question related service
public class questionController {// aquestion controller class to  have all controller method related to question
        @Autowired//autowiring for automatic dependency injection
        QuestionService questionService;  //dependencyy injected on the questionService
    @GetMapping("allQuestions") //a getreuqest to request all questions
    public ResponseEntity<List<Question>> getAllQuestions(){ //responses witha list of question

            return questionService.getAllQuestions(); //calls the getallquestions method in questionservice

    }
    @GetMapping("category/{category}")  //a getreuqest to request all questions by category consist of category name as endpoint
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){// a path variabel to assigna category name
        return questionService.getQuestionsByCategory(category);//calls a method from question serrvice and adds category

    }

    @PostMapping("add") //a post request to post a question
    public ResponseEntity<String>addQuestion(@RequestBody Question question){ //a response enetity as a sritng to say succes aor not

        return questionService.addQuestion(question); // sends the question models body
    }


}
