package com.example.xyz.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.xyz.dto.ProdutoDTO;
import com.example.xyz.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        return ResponseEntity.ok().body(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        try {
            ProdutoDTO dto = produtoService.buscarPorId(id);               
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoDTO produto) {
        try {
            ProdutoDTO dto = produtoService.criarProduto(produto);               
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
        try {
            ProdutoDTO dto = produtoService.atualizarProduto(id, produto);  
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}

