package com.knapp.oficina.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.knapp.oficina.model.FotoVeiculo;
import com.knapp.oficina.service.FotoVeiculoService;
import com.knapp.oficina.service.FotoVeiculoUploadService;

@RestController
@RequestMapping("/fotos")
public class FotoVeiculoController {

    private final FotoVeiculoService service;
    private final FotoVeiculoUploadService serviceUpload;

    public FotoVeiculoController(FotoVeiculoService service, FotoVeiculoUploadService serviceUpload) {
        this.service = service;
        this.serviceUpload = serviceUpload;
    }

    @GetMapping
    public List<FotoVeiculo> listar() {
        return service.listar();
    }

    @PostMapping
    public FotoVeiculo salvar(@RequestBody FotoVeiculo foto) {
        return service.salvar(foto);
    }

    @PostMapping("/upload/{veiculoId}")
    public ResponseEntity<List<FotoVeiculo>> uploadFotos(
            @PathVariable Long veiculoId,
            @RequestParam("arquivos") MultipartFile[] arquivos) {
        try {
            List<FotoVeiculo> fotos = serviceUpload.uploadFotos(veiculoId, arquivos);
            return ResponseEntity.ok(fotos);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

