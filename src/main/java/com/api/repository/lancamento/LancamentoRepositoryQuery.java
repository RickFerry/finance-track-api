package com.api.repository.lancamento;

import com.api.model.Lancamento;
import com.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {
    List<Lancamento> filtrar(LancamentoFilter filter);
}
