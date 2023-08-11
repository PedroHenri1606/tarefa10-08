package com.tarefa108.tarefa108.service;

import com.tarefa108.tarefa108.entity.Pessoa;
import com.tarefa108.tarefa108.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Pessoa> listarTodos(){
        return repository.findAll();
    }

    public Pessoa cadastrar(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public Pessoa editar(Long id,Pessoa pessoa){
        final Pessoa pessoaBanco = this.buscarPorId(id);

        if(pessoaBanco == null || !pessoaBanco.getId().equals(pessoa.getId())){
            System.out.println(pessoaBanco.getId());
            System.out.println(pessoa.getId());
            throw new RuntimeException("não foi possivel identificar a pessoa informada!");
        }
        return repository.save(pessoa);
    }

    public void deletar(Long id){
         repository.deleteById(id);
    }
}
