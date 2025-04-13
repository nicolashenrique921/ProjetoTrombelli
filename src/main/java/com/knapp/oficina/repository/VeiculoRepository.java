package com.knapp.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knapp.oficina.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByClienteId(Long clienteId);
}
