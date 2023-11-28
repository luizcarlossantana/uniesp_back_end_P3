package com.alunoonline.api.model.dtos.DisciplinaDTO;

import com.alunoonline.api.model.Professor;
import lombok.Data;

@Data
public class DisciplinaDTO {

    private Long id;
    private String nome;
    private Professor professor;
}
