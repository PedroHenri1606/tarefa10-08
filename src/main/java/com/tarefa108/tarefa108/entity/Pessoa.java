package com.tarefa108.tarefa108.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoas")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;


    @Getter @Setter
    private String nome;
}
