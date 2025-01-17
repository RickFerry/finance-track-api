package com.api.repository.pessoa;

import com.api.model.Pessoa;
import com.api.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {
    Page<Pessoa> filtrar(PessoaFilter filter, Pageable page);
}
