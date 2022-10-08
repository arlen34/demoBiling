package com.example.demobiling.entites;

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

    private String name;

    private String title;

    private Boolean isActive;

    private Integer numberOfReplays;

    private String imageLink;

    private Integer duration;

    private String shortDescription;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> options;
}