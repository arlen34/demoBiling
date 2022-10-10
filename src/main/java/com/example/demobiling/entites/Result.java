package com.example.demobiling.entites;

import com.example.demobiling.entites.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Client client;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST})
    private Test test;
}