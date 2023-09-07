package com.tarefa108.tarefa108.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_lembrete")
@NoArgsConstructor
@AllArgsConstructor
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String conteudo;

    @ManyToOne()
    @JoinColumn(name = "pessoa_id")
    @Getter @Setter
    private Pessoa pessoa;
}
