package com.knapp.oficina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.knapp.oficina.model.Veiculo;
import com.knapp.oficina.repository.VeiculoRepository;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public List<Veiculo> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

