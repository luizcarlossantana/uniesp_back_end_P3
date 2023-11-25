package com.alunoonline.api.model.dtos.matricula;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.Disciplina;
import com.alunoonline.api.model.enums.StatusMatricula;
import lombok.Data;

@Data
public class MatriculaAlunoDTO {

    private Long id;
    private Aluno aluno;
    private Disciplina disciplina;
    private Double nota1;
    private Double nota2;
    private StatusMatricula status;
}
