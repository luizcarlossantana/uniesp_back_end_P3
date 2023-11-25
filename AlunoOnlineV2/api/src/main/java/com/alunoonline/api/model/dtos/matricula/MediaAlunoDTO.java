package com.alunoonline.api.model.dtos.matricula;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.Disciplina;
import com.alunoonline.api.model.enums.StatusMatricula;
import lombok.Data;

@Data
public class MediaAlunoDTO {


    private String aluno;
    private String disciplina;
    private Double nota1;
    private Double nota2;
    private StatusMatricula status;
    private Double media;
}
