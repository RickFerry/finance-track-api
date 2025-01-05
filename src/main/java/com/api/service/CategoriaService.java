package com.api.service;

import com.api.model.Categoria;
import com.api.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<Categoria> listar(Pageable page) {
        return categoriaRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorCodigo(Long codigo) {
        Categoria categoria = categoriaRepository.findOne(codigo);
        if (categoria == null) {
            throw new RuntimeException("Categoria não encontrada");
        }
        return categoria;
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
