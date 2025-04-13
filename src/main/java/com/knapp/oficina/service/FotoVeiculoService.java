package com.knapp.oficina.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.knapp.oficina.model.FotoVeiculo;
import com.knapp.oficina.repository.FotoVeiculoRepository;

@Service
public class FotoVeiculoService {

    private final FotoVeiculoRepository repository;

    public FotoVeiculoService(FotoVeiculoRepository repository) {
        this.repository = repository;
    }

    public List<FotoVeiculo> listar() {
        return repository.findAll();
    }

    public FotoVeiculo salvar(FotoVeiculo foto) {
        return repository.save(foto);
    }
}

