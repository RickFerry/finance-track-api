package com.api.resource;

import com.api.model.Lancamento;
import com.api.model.dto.ResumoLancamento;
import com.api.repository.filter.LancamentoFilter;
import com.api.service.LancamentoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/lancamentos")
public class LancamentoController {
    private final LancamentoService lancamentoService;

    @GetMapping("/todos-paginado")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Page<Lancamento>> listar(Pageable page) {
        return ResponseEntity.ok(lancamentoService.listar(page));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable page) {
        return ResponseEntity.ok(lancamentoService.pesquisar(filter, page));
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Page<ResumoLancamento>> resumir(LancamentoFilter filter, Pageable page) {
        return ResponseEntity.ok(lancamentoService.resumir(filter, page));
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo) {
        try {
            return ResponseEntity.ok(lancamentoService.buscarPorCodigo(codigo));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder uriBuilder) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        return ResponseEntity.created(
                uriBuilder.path("/{codigo}").buildAndExpand(lancamentoSalvo.getCodigo()).toUri()).body(lancamentoSalvo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
        try {
            Lancamento lancamentoSalvo = lancamentoService.atualizar(codigo, lancamento);
            return ResponseEntity.ok(lancamentoSalvo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        lancamentoService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}
