package com.alunoonline.api.controller;

import com.alunoonline.api.model.Professor;
import com.alunoonline.api.model.dtos.professor.ProfessorDTO;
import com.alunoonline.api.model.dtos.professor.ProfessorFindDTO;
import com.alunoonline.api.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professor) {
        ProfessorDTO professorCreated = service.create(professor);

        return ResponseEntity.status(201).body(professorCreated);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorFindDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Professor> update(@RequestBody Professor professor){
        Professor professorUpdated = service.update(professor);

        return ResponseEntity.status(204).body(professorUpdated);
    }
}
