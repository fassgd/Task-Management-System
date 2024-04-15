package com.api.SGT.servicos;

import com.api.SGT.modelos.ModeloTarefa;
import com.api.SGT.repositorios.RepositorioTarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class ServicoTarefa {

    private final RepositorioTarefa repositorioTarefa;

    @Transactional
    public ModeloTarefa salvar(ModeloTarefa modeloTarefa) {
        return repositorioTarefa.save(modeloTarefa);
    }

    public List<ModeloTarefa> retornarTudo() {
        return repositorioTarefa.findAll();
    }

    public Optional<ModeloTarefa> retornarUmElemento(UUID id) {
        return repositorioTarefa.findById(id);
    }

    public void deletar(ModeloTarefa modeloTarefa) {
        repositorioTarefa.delete(modeloTarefa);
    }
}
