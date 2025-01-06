package com.api.resource;

import com.api.model.Categoria;
import com.api.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> listar(Pageable page) {
        return ResponseEntity.ok(categoriaService.listar(page));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Long codigo) {
        try {
            return ResponseEntity.ok(categoriaService.buscarPorCodigo(codigo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria, UriComponentsBuilder uriBuilder) {
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return ResponseEntity.created(
                uriBuilder.path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri()
        ).body(categoriaSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaService.atualizar(codigo, categoria));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        try {
            categoriaService.deletar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
