package com.api.model.dto;

import com.api.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumoLancamento {

        private Long codigo;
        private String descricao;
        private LocalDate dataVencimento;
        private LocalDate dataPagamento;
        private BigDecimal valor;
        private TipoLancamento tipo;
        private String categoria;
        private String pessoa;
}
