package com.api.service;

import com.api.exception.PessoaInexistenteOuInativaException;
import com.api.model.Lancamento;
import com.api.model.Pessoa;
import com.api.model.dto.ResumoLancamento;
import com.api.repository.filter.LancamentoFilter;
import com.api.repository.lancamento.LancamentoRepository;
import com.api.repository.pessoa.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable page) {
        return lancamentoRepository.filtrar(filter, page);
    }

    @Transactional(readOnly = true)
    public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable page) {
        return lancamentoRepository.resumir(filter, page);
    }

    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException("Pessoa inexistente ou inativa");
        }
        return lancamentoRepository.save(lancamento);
    }

    @Transactional
    public void deletar(Long codigo) {
        lancamentoRepository.delete(codigo);
    }

    @Transactional
    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = getLancamento(codigo);
        if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
            validarPessoa(lancamento);
        }
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
        return lancamentoRepository.save(lancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getCodigo() != null) {
            pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        }

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException("Pessoa inexistente ou inativa");
        }
    }

    private Lancamento getLancamento(Long codigo) {
        Lancamento lancamento = lancamentoRepository.findOne(codigo);
        if (lancamento == null) {
            throw new RuntimeException("Lançamento não encontrado");
        }
        return lancamento;
    }
}
