package com.biblioteca.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.service.BibliotecaService;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class BibliotecaController {

    @Autowired
    private BibliotecaService service;

    @GetMapping
    public List<Livro> listar() {
        return service.listarLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        return service.buscarLivro(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Livro adicionar(@RequestBody Livro livro) {
        return service.adicionarLivro(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        return service.atualizarLivro(id, livro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluirLivro(id);
    }

    @PutMapping("/{id}/emprestar")
    public ResponseEntity<?> emprestar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.emprestarLivro(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Livro> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(service.devolverLivro(id));
    }
}
