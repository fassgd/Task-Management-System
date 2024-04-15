package com.api.SGT.controladores;

import com.api.SGT.dtos.DtoTarefa;
import com.api.SGT.modelos.ModeloTarefa;
import com.api.SGT.servicos.ServicoTarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "SGT")
@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping("/sgt")
@RequiredArgsConstructor
public class ControladorTarefa {

    private final ServicoTarefa servicoTarefa;

    @Operation(summary = "Método responsável por cadastrar tarefas", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro feito com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválidos")
    })
    @PostMapping
    public ResponseEntity<Object> salvarTarefa (@RequestBody @Valid DtoTarefa dtoTarefa){
        var modeloTarefa = new ModeloTarefa();
        BeanUtils.copyProperties(dtoTarefa, modeloTarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoTarefa.salvar(modeloTarefa));
    }

    @Operation(summary = "Método responsável por retornar todas as tarefas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválidos")
    })
    @GetMapping
    public ResponseEntity<List<ModeloTarefa>> retornarTodasAsTarefas (){
        return ResponseEntity.status(HttpStatus.OK).body(servicoTarefa.retornarTudo());
    }

    @Operation(summary = "Método responsável por retornar tarefa especificada " +
            "via identificador", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválidos")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> retornarTarefa (@PathVariable (value = "id") UUID id){
        Optional<ModeloTarefa> modeloTarefa = servicoTarefa.retornarUmElemento(id);
        if (!modeloTarefa.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa inexistente");
        }
        return ResponseEntity.status(HttpStatus.OK).body(modeloTarefa.get());
    }

    @Operation(summary = "Método responsável por deletar tarefa especificada " +
            "via identificador", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Remoção feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválidos")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTarefa (@PathVariable (value = "id") UUID id){
        Optional<ModeloTarefa> modeloTarefa = servicoTarefa.retornarUmElemento(id);
        if (!modeloTarefa.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa inexistente");
        }
        servicoTarefa.deletar(modeloTarefa.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("mensagem","Remoção feita com sucesso"));
    }

    @Operation(summary = "Método responsável por atualizar tarefa especificada " +
            "via identificador", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualização feita com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTarefa (@PathVariable (value = "id") UUID id,
                                                   @RequestBody @Valid DtoTarefa dtoTarefa){
        Optional<ModeloTarefa> modeloTarefa = servicoTarefa.retornarUmElemento(id);
        if (!modeloTarefa.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa inexistente");
        }
        var mT = new ModeloTarefa();
        BeanUtils.copyProperties(dtoTarefa, mT);
        mT.setId(modeloTarefa.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(servicoTarefa.salvar(mT));
    }

}
