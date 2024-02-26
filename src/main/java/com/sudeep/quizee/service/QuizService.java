package com.sudeep.quizee.service;

import com.sudeep.quizee.dao.QuestionDao;
import com.sudeep.quizee.model.Question;
import com.sudeep.quizee.model.Quiz;
import com.sudeep.quizee.dao.QuizDao;
import com.sudeep.quizee.model.Response;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.sudeep.quizee.model.QuestionWrapper;
//these are all the packages which are getting imported because of the annotations you are using

@Service   //a service annotation to showcase that this quizservice is a service class and holda all business logic
public class QuizService { //a quiz service to hold all quiz related methods

    @Autowired  //autowired for auto dependency injection
    QuizDao quizDao;  //autowiring the quidao
    @Autowired //autowiring the questiondao
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions =questionDao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz=new Quiz(); //from the quiz model class it create an object of quiz
        quiz.setTitle(title);//sets a title for the quiz
        quiz.setQuestions(questions); //set questions for the quiz
        quizDao.save(quiz);//save the quiz using the quizdao interface
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
        //returns response as success when created

        //a quiz creating method with response type as string
        //a list of questions it finds it using a findRandomQuestionByCategory method in the question dao which has a query to fetch such result
        //it create a

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {//a method to get quiz by id
        //return a respone enetity of questionWrpper type
       Optional <Quiz> quiz = quizDao.findById(id); //optional becoz may or may not find a quiz of given id
        //finds a quiz from quiz dao
       List<Question> questionFromDB=quiz.get().getQuestions();//gets question from of quiz
       List<QuestionWrapper> questionsForUser =new ArrayList<>();//a list with questionwrapper  fro uestion for user
       for (Question q: questionFromDB){//for all the question fetched from db
           QuestionWrapper qw =new QuestionWrapper(q.getId(),q.getQuestion1(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionsForUser.add(qw);
           //adds for questio for user;
       }

       return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
       //return question for user of type questionWrapper


    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {//a calculate result function
        Quiz quiz=  quizDao.findById(id).get();//catche sthe quiz in quiz entity from quizdao
        List<Question> questions=quiz.getQuestions();//crates a quiz list of question from get question
        int right=0;  //the score
        int i=0;
        for(Response response:responses){
            if (response.getResponse().equals(questions.get(i).getAnswer()))
                right++; //incrementing the right variable
            i++;//iteratring the repsonse
        }
        return  new ResponseEntity<>(right,HttpStatus.OK); //return the score

    }
}
