package com.alunoonline.api.controller;

import com.alunoonline.api.model.MatriculaAluno;
import com.alunoonline.api.model.dtos.matricula.MatriculaAlunoDTO;
import com.alunoonline.api.model.dtos.matricula.MediaAlunoDTO;
import com.alunoonline.api.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MatriculaAlunoDTO matriculaAluno) {

        service.create(matriculaAluno);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MediaAlunoDTO> findMatriculaPorMedia(@PathVariable Long id){

        MediaAlunoDTO matriculaCalculada = service.findMatriculaByMedia(id);

        return ResponseEntity.ok().body(matriculaCalculada);

    }



}
