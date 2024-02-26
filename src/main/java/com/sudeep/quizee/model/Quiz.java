package com.sudeep.quizee.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// a quiz model to to create a quiz db structure--> it is how attributes are there in quiz
@Entity //mark a Java class as an entity. An entity in this context typically represents a table in a relational database.
//Each instance of an entity class corresponds to a row in the corresponding table
@Data // for lombok getter and setter
public class Quiz { //a quiz class of model
    @Id //marking id field as  primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY) //for generating unique primarykey for each quiz created
    private Integer id; //id and data type
    private String title; //title of quiz and datatype

    @ManyToMany
    private List<Question> questions;
    // it establishes a many-to-many relationship between the Quiz entity and the Question entity.
}
