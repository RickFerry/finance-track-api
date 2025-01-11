package com.api.resource;

import com.api.model.Lancamento;
import com.api.repository.filter.LancamentoFilter;
import com.api.service.LancamentoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/lancamentos")
public class LancamentoController {
    private final LancamentoService lancamentoService;

    @GetMapping("/todos-paginado")
    public ResponseEntity<Page<Lancamento>> listar(Pageable page) {
        return ResponseEntity.ok(lancamentoService.listar(page));
    }

    @GetMapping
    public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable page) {
        return ResponseEntity.ok(lancamentoService.pesquisar(filter, page));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo) {
        try {
            return ResponseEntity.ok(lancamentoService.buscarPorCodigo(codigo));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder uriBuilder) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        return ResponseEntity.created(
                uriBuilder.path("/{codigo}").buildAndExpand(lancamentoSalvo.getCodigo()).toUri()).body(lancamentoSalvo);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        lancamentoService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}
