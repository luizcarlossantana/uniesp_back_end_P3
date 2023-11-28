package com.alunoonline.api.service;

import com.alunoonline.api.exception.AtributosNulosException;
import com.alunoonline.api.exception.IdNaoEncontadoException;
import com.alunoonline.api.model.Professor;
import com.alunoonline.api.model.dtos.professor.ProfessorDTO;
import com.alunoonline.api.model.dtos.professor.ProfessorFindDTO;
import com.alunoonline.api.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository repository;

    public ProfessorDTO create(ProfessorDTO professorDTO){

        Professor professor = new Professor();

        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());

        validateProfessorCreated(professor);
        Professor professorCriado = repository.save(professor);

        professorDTO.setId(professorCriado.getId());

        return professorDTO;
    }

    public List<Professor> findAll(){
        return repository.findAll();
    }

    public ProfessorFindDTO findById(Long id){

        validateProfessorNull(id);
        Optional<Professor> professorBuscado = repository.findById(id);
        ProfessorFindDTO professorEncontrado = new ProfessorFindDTO();

        professorEncontrado.setNome(professorBuscado.get().getNome());

        return professorEncontrado;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Professor update(Professor professor) {
        if (professor.getNome() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não pode ser nulo");
        }
        if (professor.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail não pode ser nulo");
        }
        return repository.save(professor);
    }

    private void validateProfessorCreated(Professor professor){

        if (professor.getNome() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo");
        }
        if (professor.getEmail() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo");
        }
    }

    private Professor validateProfessorNull(Long id)  {

        Optional<Professor> professorCreated = repository.findById(id);

        if (professorCreated.isEmpty()){
            throw new IdNaoEncontadoException("Id não encontrado ");
        }
        else {
            return professorCreated.get();
        }

    }
}
