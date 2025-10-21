package com.biblioteca.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteca.biblioteca.model.Livro;

@Repository
public interface AlmoxarifeRepository extends JpaRepository<Livro, Long> {

}
