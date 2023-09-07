package com.tarefa108.tarefa108.controller;

import com.tarefa108.tarefa108.entity.Pessoa;
import com.tarefa108.tarefa108.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Pessoa>> listarTodos(){
        try {
            return ResponseEntity.ok().body(service.listarTodos());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody final Pessoa pessoa){
        try {
            return ResponseEntity.ok().body(service.cadastrar(pessoa));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<String> editar(@RequestParam("id") final Long id, @RequestBody Pessoa pessoa){
        try {
            service.editar(id,pessoa);
            return ResponseEntity.ok("Registro editado com sucesso!");
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
