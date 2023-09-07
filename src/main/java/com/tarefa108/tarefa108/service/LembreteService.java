package com.tarefa108.tarefa108.service;

import com.tarefa108.tarefa108.entity.Lembrete;
import com.tarefa108.tarefa108.entity.Pessoa;
import com.tarefa108.tarefa108.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository repository;

    public Lembrete buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Lembrete> listarPorPessoa(String nome){
        return repository.listarPorNome(nome);
    }

    public List<Lembrete> listarTodos(){
        return repository.findAll();
    }

    public Lembrete cadastrar(Lembrete lembrete){
        return repository.save(lembrete);
    }

    public Lembrete editar(Long id, Lembrete lembrete){
        final Lembrete lembreteBanco = this.buscarPorId(id);

        if(lembreteBanco == null || !lembreteBanco.getId().equals(lembrete.getId())){
            throw new RuntimeException("não foi possivel identificar o lembrete informado!");
        }
        return repository.save(lembrete);
    }
}
