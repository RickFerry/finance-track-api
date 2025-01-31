package com.api.service;

import com.api.model.Permissao;
import com.api.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

    @Transactional(readOnly = true)
    public Page<Permissao> listar(Pageable page) {
        return permissaoRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Permissao buscarPorId(Long id) {
        Permissao permissao = permissaoRepository.findOne(id);
        if (permissao == null) {
            throw new RuntimeException("Permissão não encontrada");
        }
        return permissao;
    }
}
