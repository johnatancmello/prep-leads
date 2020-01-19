package com.prep.api.controller.imagem;

import com.prep.api.model.imagem.Imagem;
import com.prep.api.model.imagem.ImagemFilter;
import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.repository.imagem.ImagemRepository;
import com.prep.api.repository.imagem.ImagemSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository repository;

    @Autowired
    private ImagemSummaryRepository summaryRepository;

    public Page<Imagem> filter(ImagemFilter filter, Pageable pageable){
        return repository.filter(filter, pageable);
    }

    public Page<Imagem> filterInSummary(ImagemFilter filter, Pageable pageable){
        return summaryRepository.filter(filter, pageable);
    }

    public Imagem getFuncionariobyId(Long id) {
        Optional<ImagemJPA> optionalImagemJPA = repository.findById(id);
        return optionalImagemJPA.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
    }


    public ResponseEntity<ImagemJPA> add(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if (fileName.contains("..")) {
            // TODO: arruamr throw new FileStorageException("Sorry! Filename contains
            // invalid path sequence " + fileName);
        }

        ImagemJPA imagem = new ImagemJPA();
        imagem.setNome(fileName);
        imagem.setTipo(file.getContentType());
        imagem.setDados(file.getBytes());
        imagem.setTamanho(file.getSize());

        return ResponseEntity.ok(repository.save(imagem));
    }
}
