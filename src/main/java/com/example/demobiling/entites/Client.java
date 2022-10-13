package com.example.demobiling.entites;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(generator = "client_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "client_generator", sequenceName = "client_id_sequence", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private AuthInfo authInfo;

}