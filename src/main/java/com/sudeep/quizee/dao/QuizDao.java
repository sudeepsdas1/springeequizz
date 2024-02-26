package com.sudeep.quizee.dao;

import com.sudeep.quizee.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


// aquiz dao
public interface QuizDao extends JpaRepository<Quiz,Integer> {


}
