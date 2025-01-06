package com.api.resource;

import com.api.model.Pessoa;
import com.api.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listar(Pageable page) {
        return ResponseEntity.ok(pessoaService.listar(page));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo) {
        try {
            return ResponseEntity.ok(pessoaService.buscarPorCodigo(codigo));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        Pessoa pessoaSalva = pessoaService.salvar(pessoa);
        return ResponseEntity.created(
                uriBuilder.path("/{codigo}").buildAndExpand(pessoaSalva.getCodigo()).toUri()).body(pessoaSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        try {
            return ResponseEntity.ok(pessoaService.atualizar(codigo, pessoa));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        pessoaService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{codigo}/ativo")
    public ResponseEntity<Void> trocaStatus(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        pessoaService.trocaStatus(codigo, ativo);
        return ResponseEntity.noContent().build();
    }
}
