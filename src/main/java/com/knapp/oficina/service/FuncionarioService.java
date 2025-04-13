package com.knapp.oficina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.knapp.oficina.model.Funcionario;
import com.knapp.oficina.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Optional<Funcionario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }
}
