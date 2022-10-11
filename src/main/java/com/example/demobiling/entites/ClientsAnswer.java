package com.example.demobiling.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "clients_answers")
@Getter
@Setter
@NoArgsConstructor
public class ClientsAnswer {

    @Id
    @GeneratedValue(generator = "clients_answer_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clients_answer_generator", sequenceName = "clients_answer_id_sequence", allocationSize = 1)
    private Long id;

    private int numberOfWords;//user's answer's counter words

    private int score;

    @OneToOne(cascade = ALL)
    private Content content;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH},fetch = FetchType.LAZY)
    private Question question;

    @OneToMany(cascade = ALL, mappedBy = "clientsAnswer")
    List<Option> optionsAnswers;

    @ManyToOne(cascade = {DETACH,PERSIST,MERGE,REMOVE})
    private Result result;


}
