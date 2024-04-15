package com.api.SGT.repositorios;

import com.api.SGT.modelos.ModeloTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioTarefa extends JpaRepository <ModeloTarefa, UUID>{
}
