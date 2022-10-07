package com.example.demobiling.entity;

import com.example.demobiling.entity.enums.OptionType;
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

}
