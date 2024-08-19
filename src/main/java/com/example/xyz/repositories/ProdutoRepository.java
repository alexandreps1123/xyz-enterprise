package com.example.xyz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xyz.entities.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    
}
  