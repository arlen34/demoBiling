package com.example.demobiling.entites;

import com.example.demobiling.entites.enums.OptionType;
import com.example.demobiling.entites.enums.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(generator = "question_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "question_generator", sequenceName = "question_id_sequence", allocationSize = 1)
    private Long id;

    private String title;

    private String statement;

    private String passage;

    private Boolean isActive;

    private int numberOfReplays;

    private String image;

    private String audio;

    private int duration;

    private int numberOfWords;//counter for question type: respond in at least N words;

    private String shortDescription;

    private int minNumberOfWords;

    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private Test test;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private List<Option> options;


}