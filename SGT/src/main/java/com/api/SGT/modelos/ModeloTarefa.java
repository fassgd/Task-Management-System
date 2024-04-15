package com.api.SGT.modelos;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.server.UID;
import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "Tarefas")
@Data
public class ModeloTarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;

    @Column (nullable = false, unique = false, length = 80)
    private String titulo;

    @Column (nullable = false, unique = false, length = 5000)
    private String descricao;

    @Column (nullable = false, unique = false, length = 80)
    private String responsavel;

    @Column (nullable = false, unique = false, length = 5)
    private String prioridade;

    @Column (nullable = false, unique = false, length = 10)
    private Date deadline;

    @Column (nullable = false, unique = false, length = 12)
    private String situacao;
}
