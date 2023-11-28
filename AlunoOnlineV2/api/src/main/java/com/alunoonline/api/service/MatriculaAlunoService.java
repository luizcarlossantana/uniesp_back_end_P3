package com.alunoonline.api.service;

import com.alunoonline.api.exception.AtributosNulosException;
import com.alunoonline.api.exception.IdNaoEncontadoException;
import com.alunoonline.api.model.MatriculaAluno;
import com.alunoonline.api.model.dtos.matricula.MatriculaAlunoDTO;
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
        matriculaCriada.setNota1(matriculaAlunoDTO.getNota1());
        matriculaCriada.setNota2(matriculaAlunoDTO.getNota2());

        repository.save(matriculaCriada);

    }


    public void updateMatriculaTrancada(Long id){

        MatriculaAluno alunoMatriculado = validetedIdTrancado(id);






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

    private MatriculaAluno alunoCalculadoPelaMedia(Long id){

        MatriculaAluno aluno = validetedId(id);

        Double media = (aluno.getNota1() + aluno.getNota2())/2;

        if (media >= 7){
           aluno.setStatus(StatusMatricula.APROVADO);
        }else {
            aluno.setStatus(StatusMatricula.REPROVADO);
        }

        return aluno;

    }

    private MatriculaAluno validetedId(Long id){

        Optional<MatriculaAluno> alunoMatriculado = repository.findById(id);


        if (alunoMatriculado.isEmpty()){
            throw new IdNaoEncontadoException("Matricula não encontrada");
        }
        else {
            if (alunoMatriculado.get().getNota1() == null ||alunoMatriculado.get().getNota2() == null  ){
                throw new AtributosNulosException("Os atributos notas não podem ser nulos");

            }else {
                return alunoMatriculado.get();
            }
        }
    }

    private MatriculaAluno validetedIdTrancado(Long id){

        Optional<MatriculaAluno> alunoMatriculado = repository.findById(id);


        if (alunoMatriculado.isEmpty()){
            throw new IdNaoEncontadoException("Matricula não encontrada");
        }else {
            return alunoMatriculado.get();
        }

    }




}
