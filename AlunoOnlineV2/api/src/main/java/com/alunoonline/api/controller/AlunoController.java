package com.alunoonline.api.controller;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.dtos.aluno.AlunoDTO;
import com.alunoonline.api.model.dtos.aluno.AlunoFindDTO;
import com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Identificação da classe Controller no Spring com a arquitetura REST
@RestController
// Mapeamento das requisições do FrontEnd
@RequestMapping("/aluno")
public class AlunoController {

    // Injeção dos métodos dos service
    @Autowired
    AlunoService service;

    // Requisição do tipo POST
    @PostMapping
    // Resposta da requisição
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO alunoCreated = service.create(alunoDTO);

        return ResponseEntity.status(201).body(alunoCreated);
    }

    // Requisição do tipo GET
    @GetMapping("/all")
    // Resposta da requisição
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll(){
        return service.findAll();
    }

    // Requisição do tipo GET
    @GetMapping("/{id}")
    // Resposta da requisição
    @ResponseStatus(HttpStatus.OK)
    public AlunoFindDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    // Requisição do tipo DELETE
    @DeleteMapping("/{id}")
    // Resposta da requisição
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //requisição do tipo PUT
    @PutMapping("/{id}")
    // Resposta da requisição
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id,@RequestBody AlunoDTO aluno){
        AlunoDTO alunoUpdate = service.update(id,aluno);

        return ResponseEntity.status(204).body(alunoUpdate);
    }


}
