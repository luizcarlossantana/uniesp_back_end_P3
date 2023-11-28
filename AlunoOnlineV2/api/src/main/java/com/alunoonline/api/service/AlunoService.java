package com.alunoonline.api.service;

import com.alunoonline.api.exception.AtributosNulosException;
import com.alunoonline.api.exception.IdNaoEncontadoException;
import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.dtos.aluno.AlunoDTO;
import com.alunoonline.api.model.dtos.aluno.AlunoFindDTO;
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
    public AlunoDTO create(AlunoDTO alunoDTO){

        Aluno aluno = new Aluno();

        aluno.setNome(alunoDTO.getNome());
        aluno.setCurso(alunoDTO.getCurso());
        aluno.setEmail(alunoDTO.getEmail());

        validateAlunoCreated(aluno);
        Aluno alunoCriado = repository.save(aluno);

        alunoDTO.setId(alunoCriado.getId());


        return alunoDTO;
    }

    // método para buscar todos os alunos
    public List<Aluno> findAll(){
        return repository.findAll();
    }

    //método para buscar um aluno pelo Id
    public AlunoFindDTO findById(Long id){

        validateAlunoNull(id);
       Optional<Aluno> alunoBuscado =  repository.findById(id);
       AlunoFindDTO alunoEncontrado = new AlunoFindDTO();

       alunoEncontrado.setCurso(alunoBuscado.get().getCurso());
       alunoEncontrado.setNome(alunoBuscado.get().getNome());

        return alunoEncontrado ;
    }

    // método para deletar um aluno pelo Id
    public void delete(Long id){

        validateAlunoNull(id);
        repository.deleteById(id);
    }

    //método para atualizar um aluno pelo Id
     public AlunoDTO update(Long id, AlunoDTO alunoDTO){

        Aluno alunoUpdated = validateAlunoNull(id);

        if (alunoDTO.getNome() != null) {
            alunoUpdated.setNome(alunoDTO.getNome());
        }
        if (alunoDTO.getCurso() != null) {
            alunoUpdated.setCurso(alunoDTO.getCurso());
        }
        if (alunoDTO.getEmail() != null) {
            alunoUpdated.setEmail(alunoDTO.getEmail());
        }
        Aluno alunoAtualizado = repository.save(alunoUpdated);

        alunoDTO.setEmail(alunoAtualizado.getEmail());
        alunoDTO.setNome(alunoAtualizado.getNome());
        alunoDTO.setCurso(alunoAtualizado.getCurso());
        alunoDTO.setId(alunoAtualizado.getId());

        return alunoDTO ;

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
