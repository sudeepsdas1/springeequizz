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

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions =questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
       Optional <Quiz> quiz = quizDao.findById(id);
       List<Question> questionFromDB=quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser =new ArrayList<>();
       for (Question q: questionFromDB){
           QuestionWrapper qw =new QuestionWrapper(q.getId(),q.getQuestion1(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionsForUser.add(qw);
       }

       return new ResponseEntity<>(questionsForUser,HttpStatus.OK);


    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=  quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response:responses){
            if (response.getResponse().equals(questions.get(i).getAnswer()))
                right++;
            i++;
        }
        return  new ResponseEntity<>(right,HttpStatus.OK);

    }
}
