package com.api.SGT.controladoresTestes;

import com.api.SGT.controladores.ControladorTarefa;
import com.api.SGT.dtos.DtoTarefa;
import com.api.SGT.modelos.ModeloTarefa;
import com.api.SGT.servicos.ServicoTarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControladorTarefaTest {

    @Mock
    private ServicoTarefa servicoTarefa;

    @InjectMocks
    private ControladorTarefa controladorTarefa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarTarefa() {
        DtoTarefa dtoTarefa = new DtoTarefa();
        ModeloTarefa modeloTarefa = new ModeloTarefa();
        when(servicoTarefa.salvar(any(ModeloTarefa.class))).thenReturn(modeloTarefa);

        ResponseEntity<Object> response = controladorTarefa.salvarTarefa(dtoTarefa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(modeloTarefa, response.getBody());
    }

    @Test
    void retornarTodasAsTarefas() {
        List<ModeloTarefa> tarefas = Arrays.asList(new ModeloTarefa(), new ModeloTarefa());
        when(servicoTarefa.retornarTudo()).thenReturn(tarefas);

        ResponseEntity<List<ModeloTarefa>> response = controladorTarefa.retornarTodasAsTarefas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefas, response.getBody());
    }

    @Test
    void retornarTarefa() {
        UUID id = UUID.randomUUID();
        ModeloTarefa modeloTarefa = new ModeloTarefa();
        when(servicoTarefa.retornarUmElemento(id)).thenReturn(Optional.of(modeloTarefa));

        ResponseEntity<Object> response = controladorTarefa.retornarTarefa(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(modeloTarefa, response.getBody());
    }

    @Test
    void testAtualizarTarefa() {
        UUID id = UUID.randomUUID();
        DtoTarefa dtoTarefa = new DtoTarefa();
        dtoTarefa.setTitulo("Nova Tarefa");

        ModeloTarefa modeloTarefaExistente = new ModeloTarefa();
        modeloTarefaExistente.setId(id);
        Optional<ModeloTarefa> optionalModeloTarefa = Optional.of(modeloTarefaExistente);
        when(servicoTarefa.retornarUmElemento(id)).thenReturn(optionalModeloTarefa);

        ResponseEntity<Object> responseEntity = controladorTarefa.atualizarTarefa(id, dtoTarefa);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(servicoTarefa, times(1)).salvar(any(ModeloTarefa.class));
    }

    @Test
    void testDeletarTarefa() {

        UUID id = UUID.randomUUID();

        ModeloTarefa modeloTarefaExistente = new ModeloTarefa();
        modeloTarefaExistente.setId(id);
        Optional<ModeloTarefa> optionalModeloTarefa = Optional.of(modeloTarefaExistente);
        when(servicoTarefa.retornarUmElemento(id)).thenReturn(optionalModeloTarefa);

        ResponseEntity<Object> responseEntity = controladorTarefa.deletarTarefa(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(servicoTarefa, times(1)).deletar(modeloTarefaExistente);
    }

}
