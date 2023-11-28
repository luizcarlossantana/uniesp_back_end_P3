package com.alunoonline.api.service;

import com.alunoonline.api.exception.AtributosNulosException;
import com.alunoonline.api.model.Disciplina;
import com.alunoonline.api.model.dtos.DisciplinaDTO.DisciplinaDTO;
import com.alunoonline.api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    public DisciplinaDTO create(DisciplinaDTO disciplinaDTO){

        Disciplina disciplina = new Disciplina();

        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setProfessor(disciplinaDTO.getProfessor());

        validateDisciplinaCreated(disciplina);
        Disciplina disciplinaCriada = repository.save(disciplina);

        disciplinaDTO.setId(disciplinaCriada.getId());

        return disciplinaDTO;
    }

    public List<Disciplina> findByProfessorId(Long professorId){
        return repository.findByProfessorId(professorId);
    }

    private void validateDisciplinaCreated(Disciplina disciplina){

        if (disciplina.getNome() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo ");
        }
        if (disciplina.getProfessor() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo ");
        }
    }
}
