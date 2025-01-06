package com.api.service;

import com.api.model.Categoria;
import com.api.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    @Transactional
    public Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria categoriaSalva = buscarPorCodigo(codigo);
        if (categoriaSalva == null) {
            throw new RuntimeException("Categoria não encontrada");
        }
        BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
        return categoriaRepository.save(categoriaSalva);
    }

    @Transactional
    public void deletar(Long codigo) {
        Categoria categoria = buscarPorCodigo(codigo);
        if (categoria == null) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriaRepository.delete(categoria);
    }
}
