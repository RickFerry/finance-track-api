package com.api.service;

import com.api.model.Pessoa;
import com.api.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public Page<Pessoa> listar(Pageable page) {
        return pessoaRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorCodigo(Long codigo) {
        return getPessoa(codigo);
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = getPessoa(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    @Transactional
    public void deletar(Long codigo) {
        pessoaRepository.delete(codigo);
    }

    public void trocaStatus(Long codigo, Boolean ativo) {
        Pessoa pessoa = getPessoa(codigo);
        pessoa.setAtivo(ativo);
        pessoaRepository.save(pessoa);
    }

    public Pessoa getPessoa(Long codigo) {
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        if (pessoa == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        return pessoa;
    }
}
