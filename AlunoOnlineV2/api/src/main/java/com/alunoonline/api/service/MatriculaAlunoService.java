package com.alunoonline.api.service;

import com.alunoonline.api.exception.AtributosNulosException;
import com.alunoonline.api.exception.IdNaoEncontadoException;
import com.alunoonline.api.model.MatriculaAluno;
import com.alunoonline.api.model.dtos.matricula.MatriculaAlunoDTO;
import com.alunoonline.api.model.dtos.matricula.MatriculaAlunoFindByIdDTO;
import com.alunoonline.api.model.dtos.matricula.MediaAlunoDTO;
import com.alunoonline.api.model.enums.StatusMatricula;
import com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository repository;

    public void create(MatriculaAlunoDTO matriculaAlunoDTO){

        MatriculaAluno matriculaCriada = new MatriculaAluno();

        matriculaCriada.setAluno(matriculaAlunoDTO.getAluno());
        matriculaCriada.setDisciplina(matriculaAlunoDTO.getDisciplina());
        matriculaCriada.setStatus(StatusMatricula.MATRICULADO);
        matriculaCriada.setNota1(0.0);
        matriculaCriada.setNota2(0.0);

        repository.save(matriculaCriada);

    }


    public void updateMatriculaTrancada(Long id){

        MatriculaAluno alunoMatriculado = validatedIdTrancado(id);

        alunoMatriculado.setStatus(StatusMatricula.TRANCADO);


    }

    public void updateNotas(Long id,MatriculaAlunoDTO matriculaAlunoDTO){

        Optional<MatriculaAluno> alunoMatriculado = repository.findById(id);

        if (matriculaAlunoDTO.getNota1()!= null){
            alunoMatriculado.get().setNota1(matriculaAlunoDTO.getNota1());
        }
        if (matriculaAlunoDTO.getNota2()!= null){
            alunoMatriculado.get().setNota2(matriculaAlunoDTO.getNota2());
        }

        repository.save(alunoMatriculado.get());
    }

    public MediaAlunoDTO findMatriculaByMedia(Long id){
       MatriculaAluno matriculaCalculada = alunoCalculadoPelaMedia(id);
       MediaAlunoDTO matriculaDTO = new MediaAlunoDTO();
       Double media = (matriculaCalculada.getNota1() + matriculaCalculada.getNota2())/2;


       matriculaDTO.setAluno(matriculaCalculada.getAluno().getNome());
       matriculaDTO.setStatus(matriculaCalculada.getStatus());
       matriculaDTO.setDisciplina(matriculaCalculada.getDisciplina().getNome());
       matriculaDTO.setNota1(matriculaCalculada.getNota1());
       matriculaDTO.setNota2(matriculaCalculada.getNota2());
       matriculaDTO.setMedia(media);

      return matriculaDTO;
    }

    public MatriculaAlunoFindByIdDTO matriculaFindById (Long id){
        MatriculaAluno matriculaAluno = validatedIdNull(id);
        MatriculaAlunoFindByIdDTO matriculaAlunoFindByIdDTO = new MatriculaAlunoFindByIdDTO();

        matriculaAlunoFindByIdDTO.setAluno(matriculaAluno.getAluno());
        matriculaAlunoFindByIdDTO.setDisciplina(matriculaAluno.getDisciplina());


        return matriculaAlunoFindByIdDTO;
    }

    private MatriculaAluno alunoCalculadoPelaMedia(Long id){

        MatriculaAluno aluno = validatedIdNull(id);

        Double media = (aluno.getNota1() + aluno.getNota2())/2;

        if (media >= 7){
           aluno.setStatus(StatusMatricula.APROVADO);
        }else {
            aluno.setStatus(StatusMatricula.REPROVADO);
        }

        return aluno;

    }

    private MatriculaAluno validatedIdNull(Long id) {

        Optional<MatriculaAluno> alunoMatriculado = repository.findById(id);


        if (alunoMatriculado.isEmpty()) {
            throw new IdNaoEncontadoException("Matricula não encontrada");
        } else {
            return alunoMatriculado.get();
        }
    }

    private MatriculaAluno validatedNotasNull(Long id){

        MatriculaAluno alunoMatriculado = validatedIdNull(id);

        if (alunoMatriculado.getNota1() == null ||alunoMatriculado.getNota2() == null  ){
            throw new AtributosNulosException("Os atributos notas não podem ser nulos");

        }else {
            return alunoMatriculado;
        }
    }

    private MatriculaAluno validatedIdTrancado(Long id){

        MatriculaAluno alunoMatriculado = validatedIdNull(id);

            if (alunoMatriculado.getNota1() != null || alunoMatriculado.getNota2() != null  ) {
            throw new AtributosNulosException("Notas encontardas, não podemos Trancar a matricula");

            }
            else {
                return alunoMatriculado;
            }
        }
    }







