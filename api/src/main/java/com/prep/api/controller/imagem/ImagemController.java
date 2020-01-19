package com.prep.api.controller.imagem;

import com.prep.api.model.imagem.Imagem;
import com.prep.api.model.imagem.ImagemFilter;
import com.prep.api.model.imagem.ImagemJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemService service;

    @GetMapping
    public Page<Imagem> filter(ImagemFilter filter, Pageable pageable) {
        return service.filter(filter, pageable);
    }

    @GetMapping("/summary")
    public Page<Imagem> filterInSummary(ImagemFilter filter, Pageable pageable) {
        return service.filterInSummary(filter, pageable);
    }

    @PostMapping
    public ResponseEntity<ImagemJPA> add(@RequestBody MultipartFile file) throws IOException {
        return service.add(file);
    }

    @GetMapping("/{id}")
    public Imagem getFuncionarioById(@PathVariable Long id) {
        return service.getFuncionariobyId(id);
    }
}
