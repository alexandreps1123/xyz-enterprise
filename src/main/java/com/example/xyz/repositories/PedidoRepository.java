package com.example.xyz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.xyz.entities.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    
}
  