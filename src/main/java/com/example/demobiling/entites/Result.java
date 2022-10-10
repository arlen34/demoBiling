package com.example.demobiling.entites;

import com.example.demobiling.entites.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(generator = "result_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "result_generator", sequenceName = "result_id_sequence", allocationSize = 1)
    private Long id;

    private LocalDateTime dateOfSubmission;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int finalScore;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "result")
    private List<ClientsAnswer> clientsAnswers;

    @ManyToOne(cascade = {MERGE, DETACH})
    private Client client;

    @ManyToOne(cascade = {MERGE,DETACH,REFRESH,PERSIST})
    private Test test;
}