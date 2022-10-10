package com.example.demobiling.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

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



    private int numberOfWords;//user answer counter words

    private int score;

    @OneToOne(cascade = ALL)
//    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    private Question question;

    @OneToMany(cascade = ALL,mappedBy = "clientsAnswer")
    List<Option> optionsAnswers;

    @ManyToOne(cascade = ALL)
    private Result result;


}
