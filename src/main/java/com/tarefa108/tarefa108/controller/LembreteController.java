package com.tarefa108.tarefa108.controller;

import com.tarefa108.tarefa108.entity.Lembrete;
import com.tarefa108.tarefa108.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*

    {
        "id": 54,
        "conteudo": "Pedrão é foda",
        "pessoa": {
            "id": 1,
            "nome": "Pedro Henrique Vieira"
        }
    }

 */

@RestController
@RequestMapping(value = "/lembrete")
public class LembreteController {

    @Autowired
    private LembreteService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Lembrete>> listarTodos() {
        try {
            return ResponseEntity.ok().body(service.listarTodos());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/listar_por_nome")
    public ResponseEntity<List<Lembrete>> listarPorNome(@RequestParam("nome") final String nome) {
        try {
            return ResponseEntity.ok().body(service.listarPorPessoa(nome));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Lembrete> cadastrar(@RequestBody final Lembrete lembrete){
        try {
            return ResponseEntity.ok().body(service.cadastrar(lembrete));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<String> editar(@RequestParam("id") final Long id, @RequestBody Lembrete lembrete){
        try {
            service.editar(id,lembrete);
            return ResponseEntity.ok("Registro editado com sucesso!");
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try{
            service.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        }  catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
