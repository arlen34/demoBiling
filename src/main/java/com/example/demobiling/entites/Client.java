package com.example.demobiling.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(generator = "client_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "client_generator", sequenceName = "client_id_sequence", allocationSize = 1)
    private Long id;

    private String full_name;

    @OneToOne(cascade = CascadeType.ALL)
    private AuthInfo authInfo;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "clients_tests",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Test> tests;
}