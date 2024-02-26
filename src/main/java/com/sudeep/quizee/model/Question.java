package com.sudeep.quizee.model;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Data
@Entity
public class Question {  // question model class to define the question enetity struture

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //for auto identity
    private Integer id;
    private String category;
    private String difficulty;
    private String question1;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
// the attributes of question model


}
