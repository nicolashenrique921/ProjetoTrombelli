package com.knapp.oficina.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.knapp.oficina.model.FotoVeiculo;
import com.knapp.oficina.model.Veiculo;
import com.knapp.oficina.repository.FotoVeiculoRepository;
import com.knapp.oficina.repository.VeiculoRepository;

@Service
public class FotoVeiculoUploadService {

    @Value("${upload.dir}")
    private String uploadDir;

    private final FotoVeiculoRepository fotoRepo;
    private final VeiculoRepository veiculoRepo;

    public FotoVeiculoUploadService(FotoVeiculoRepository fotoRepo, VeiculoRepository veiculoRepo) {
        this.fotoRepo = fotoRepo;
        this.veiculoRepo = veiculoRepo;
    }

    public List<FotoVeiculo> uploadFotos(Long veiculoId, MultipartFile[] arquivos) throws IOException {
        Veiculo veiculo = veiculoRepo.findById(veiculoId)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        List<FotoVeiculo> fotosSalvas = new ArrayList<>();

        for (MultipartFile arquivo : arquivos) {
            if (!arquivo.isEmpty()) {
                String nomeUnico = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
                File destino = new File(uploadDir, nomeUnico);
                destino.getParentFile().mkdirs(); // garante que o diretório exista
                arquivo.transferTo(destino);

                FotoVeiculo foto = new FotoVeiculo();
                foto.setUrl("/" + uploadDir + "/" + nomeUnico);
                foto.setDataUpload(LocalDateTime.now());
                foto.setVeiculo(veiculo);

                fotosSalvas.add(fotoRepo.save(foto));
            }
        }

        return fotosSalvas;
    }
}

