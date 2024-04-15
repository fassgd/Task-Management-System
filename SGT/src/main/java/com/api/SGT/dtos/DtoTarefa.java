package com.api.SGT.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class DtoTarefa {

    @NotBlank
    @Size(max = 80)
    private String titulo;

    @NotBlank
    @Size(max = 5000)
    private String descricao;

    @NotBlank
    @Size(max = 80)
    private String responsavel;

    @NotBlank
    @Size(max = 5)
    private String prioridade;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-3")
    private Date deadline;

    @NotBlank
    @Size(max = 12)
    private String situacao;
}
