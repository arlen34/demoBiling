package com.example.demobiling.entites;

import com.example.demobiling.entites.enums.OptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(generator = "option_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "option_generator", sequenceName = "option_id_sequence", allocationSize = 1)
    private Long id;

    private String optionName;

    private String audioLink;

    private Boolean isTrue;

    private OptionType optionType;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    private Question question;

    @ManyToOne(cascade = CascadeType.ALL)
    private ClientsAnswer clientsAnswer;

}
