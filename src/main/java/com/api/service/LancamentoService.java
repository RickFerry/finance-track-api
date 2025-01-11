package com.api.service;

import com.api.exception.PessoaInexistenteOuInativaException;
import com.api.model.Lancamento;
import com.api.model.Pessoa;
import com.api.repository.filter.LancamentoFilter;
import com.api.repository.lancamento.LancamentoRepository;
import com.api.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LancamentoService {
    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public Page<Lancamento> listar(Pageable page) {
        return lancamentoRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Lancamento buscarPorCodigo(Long codigo) {
        return getLancamento(codigo);
    }

    private Lancamento getLancamento(Long codigo) {
        Lancamento lancamento = lancamentoRepository.findOne(codigo);
        if (lancamento == null) {
            throw new RuntimeException("Lançamento não encontrado");
        }
        return lancamento;
    }

    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException("Pessoa inexistente ou inativa");
        }
        return lancamentoRepository.save(lancamento);
    }

    public List<Lancamento> pesquisar(LancamentoFilter filter) {
        return lancamentoRepository.filtrar(filter);
    }
}
