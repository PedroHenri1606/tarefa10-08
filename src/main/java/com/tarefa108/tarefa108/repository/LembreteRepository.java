package com.tarefa108.tarefa108.repository;

import com.tarefa108.tarefa108.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete,Long> {

    @Query("SELECT lembrete FROM Lembrete lembrete WHERE lembrete.pessoa.nome = :nome")
    public List<Lembrete> listarPorNome(@Param("nome") final String nome);
}
