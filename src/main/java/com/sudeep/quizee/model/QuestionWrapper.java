package com.sudeep.quizee.model;

import lombok.Data;

@Data
public class QuestionWrapper {   // a wrapper class on top of question to show only that elements in quiz that are needed
    //here we need only the question name id and options


    private Integer id;
    private String question1;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
 //a question wrapper constructor
    public QuestionWrapper(Integer id, String question1, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.question1 = question1;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
