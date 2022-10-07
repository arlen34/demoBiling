package com.example.demobiling.entity;

import com.example.demobiling.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(generator = "result_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "result_generator", sequenceName = "result_id_sequence", allocationSize = 1)
    private Long id;

    private LocalDateTime dateOfSubmission;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer fullScore;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private ClientsAnswer clientsAnswer;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Client client;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Test test;

}