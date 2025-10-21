package com.biblioteca.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.AlmoxarifeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    @Autowired
    private AlmoxarifeRepository almoxarife;

    public List<Livro> listarLivros() {
        return almoxarife.findAll();
    }

    public Optional<Livro> buscarLivro(Long id) {
        return almoxarife.findById(id);
    }

    public Livro adicionarLivro(Livro livro) {
        livro.setEmprestado(false);
        return almoxarife.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro novosDados) {
        return almoxarife.findById(id).map(livro -> {
            livro.setTitulo(novosDados.getTitulo());
            livro.setAutor(novosDados.getAutor());
            livro.setGenero(novosDados.getGenero());
            return almoxarife.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void excluirLivro(Long id) {
        almoxarife.deleteById(id);
    }

    public Livro emprestarLivro(Long id) {
        Livro livro = almoxarife.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.isEmprestado()) {
            throw new RuntimeException("Livro já está emprestado!");
        }

        livro.setEmprestado(true);
        return almoxarife.save(livro);
    }

    public Livro devolverLivro(Long id) {
        Livro livro = almoxarife.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setEmprestado(false);
        return almoxarife.save(livro);
    }
}
