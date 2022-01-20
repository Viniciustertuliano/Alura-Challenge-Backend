package com.alura.challenge.controller;

import com.alura.challenge.model.Despesa;
import com.alura.challenge.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRep;

    @GetMapping
    public List<Despesa> getDespesas(){
        return despesaRep.findAll();
    }

    @GetMapping("/{despesaId}")
    public Despesa getDespesaPorId(@PathVariable Long despesaId){
        return despesaRep.findById(despesaId).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Despesa criarDispesa(@RequestBody Despesa despesa){
        return despesaRep.save(despesa);
    }

    @PutMapping("/{despesaId}")
    public ResponseEntity<Despesa> atualizaDespesa(@PathVariable Long despesaId, @RequestBody Despesa despesa){
        if (!despesaRep.existsById(despesaId)){
            return ResponseEntity.notFound().build();
        }
        despesa.setId(despesaId);
        return ResponseEntity.ok(despesaRep.save(despesa));
    }

    @DeleteMapping("/{despesaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarDespesa(@PathVariable Long despesaId){
        despesaRep.deleteById(despesaId);
    }


}
