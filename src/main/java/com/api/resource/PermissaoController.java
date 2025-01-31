package com.api.resource;

import com.api.model.Permissao;
import com.api.service.PermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissoes")
public class PermissaoController {
    private final PermissaoService permissaoService;

    @GetMapping
    public ResponseEntity<Page<Permissao>> listar(Pageable page) {
        return ResponseEntity.ok(permissaoService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(permissaoService.buscarPorId(id));
    }
}
