package com.alunoonline.api.model.dtos.matricula;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.Disciplina;
import lombok.Data;

@Data
public class MatriculaAlunoFindByIdDTO {
    private Aluno aluno;
    private Disciplina disciplina;
}
