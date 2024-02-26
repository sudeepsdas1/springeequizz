package com.sudeep.quizee.controller;
import com.sudeep.quizee.model.Response;
import com.sudeep.quizee.model.QuestionWrapper;
import com.sudeep.quizee.service.QuizService;
import com.sudeep.quizee.model.Question;

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
import org.springframework.web.bind.annotation.RequestBody;
//these are all the packages which are getting imported because of the annotations you are using



@RestController //annotation to build restful web  service
@RequestMapping("quiz")//request mapping to (base url) to hit for any quiz related service
public class quizController {    //quiz controller class which consist all quiz related services
    @Autowired  //annotation for  automatic dependency injection.
    QuizService quizService;//here for a class field of quiz service

    @PostMapping("create")//a post request endpoint
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
    return quizService.createQuiz(category,numQ,title);

    //creating a quiz controller for creating quiz which takes params as 1. the category from db 2.no.of questions to fetch 3.Title of the quiz
        // ResponseEntity<string> is a class  to represent the entire http response
        //it returns a call to the quiz service to create a quiz with the params given and returna  string response
    }

    @GetMapping("get/{id}") //a get response endpoint which takes a variable path which is id and is integer
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
     return quizService.getQuizQuestion(id);

     //the response type here is a list of the questions with question wrapper model
        //it returns the a response by calliing the quiz service  and get quizquestion method by giving it the id of the quiz it wants
    }
    @PostMapping("submit/{id}")//a post request endpoint which takes the id of the quiz to check result
    public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);

        //the return type is integer which will return the score(which is integr)
        //and a calculate result funtion is called to calculate the score;
    }

}
