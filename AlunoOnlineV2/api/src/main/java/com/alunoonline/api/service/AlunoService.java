package com.alunoonline.api.service;

import com.alunoonline.api.exception.AtributosNulosException;
import com.alunoonline.api.exception.IdNaoEncontadoException;
import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Identificação do Service do Spring
@Service
public class AlunoService {

    // Injeção dos métodos do repository
    @Autowired
    AlunoRepository repository;

    // método para criar aluno
    public Aluno create(Aluno aluno){

        validateAlunoCreated(aluno);
        return repository.save(aluno);
    }

    // método para buscar todos os alunos
    public List<Aluno> findAll(){
        return repository.findAll();
    }

    //método para buscar um aluno pelo Id
    public Optional<Aluno> findById(Long id){

        validateAlunoNull(id);
        return repository.findById(id);
    }

    // método para deletar um aluno pelo Id
    public void delete(Long id){

        validateAlunoNull(id);
        repository.deleteById(id);
    }

    //método para atualizar um aluno pelo Id
     public Aluno update(Long id, Aluno aluno){

        Aluno alunoUpdated = validateAlunoNull(id);

        if (aluno.getNome() != null) {
            alunoUpdated.setNome(aluno.getNome());
        }
        if (aluno.getCurso() != null) {
            alunoUpdated.setCurso(aluno.getCurso());
        }
        if (aluno.getEmail() != null) {
            alunoUpdated.setEmail(aluno.getEmail());
        }
        return repository.save(alunoUpdated);
    }



    private Aluno validateAlunoNull(Long id)  {

        Optional<Aluno> alunoCreated = repository.findById(id);

        if (alunoCreated.isEmpty()){
            throw new IdNaoEncontadoException("Id não encontrado ");
        }
        else {
            return alunoCreated.get();
        }

    }

    private void validateAlunoCreated(Aluno aluno){

        if (aluno.getNome() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo ");
        }
        if (aluno.getCurso() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo ");
        }
        if (aluno.getEmail() == null){
            throw new AtributosNulosException("Nenhum atributo deve ser nulo ");
        }
    }
}
