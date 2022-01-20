package com.alura.challenge.controller;

import com.alura.challenge.model.Receita;
import com.alura.challenge.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaRepository repository;

    @GetMapping
    public List<Receita> getReceitas(){
        return repository.findAll();
    }

    @GetMapping("/{receitaId}")
    public Receita getReceitaPorId(@PathVariable Long receitaId){
        return repository.findById(receitaId).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receita criarReceita(@RequestBody Receita receita){
        return repository.save(receita);
    }

    @PutMapping("/{receitaId}")
    public ResponseEntity<Receita> atualizar (@PathVariable Long receitaId, @RequestBody Receita receita){
        if (!repository.existsById(receitaId)){
            return ResponseEntity.notFound().build();
        }
        receita.setId(receitaId);
        return ResponseEntity.ok(repository.save(receita));
    }

    @DeleteMapping("/{receitaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long receitaId){
        repository.deleteById(receitaId);
    }
}
