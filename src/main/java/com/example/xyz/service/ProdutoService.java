package com.example.xyz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.xyz.adapter.ProdutoAdapter;
import com.example.xyz.domain.Produto;
import com.example.xyz.dto.ProdutoDTO;
import com.example.xyz.entities.ProdutoEntity;
import com.example.xyz.repositories.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
            .map(ProdutoAdapter::fromEntityToProdutoDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoAdapter::fromEntityToProdutoDTO)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    @Transactional
    public ProdutoDTO criarProduto(ProdutoDTO dto) {
        Produto produto = new Produto(
            dto.getNome(),
            dto.getDescricao(),
            dto.getPreco(),
            dto.getQuantidade()
        );
        
        ProdutoEntity entity =  produtoRepository.save(ProdutoAdapter.toProdutoEntity(produto));
        return ProdutoAdapter.fromEntityToProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto) {
        ProdutoEntity entity = ProdutoAdapter.fromDTOToProdutoEntity(this.buscarPorId(id));

        Produto produto = new Produto(
            dto.getNome(),
            dto.getDescricao(),
            dto.getPreco(),
            dto.getQuantidade()
        );

        entity = ProdutoAdapter.toProdutoEntity(produto);
        entity.setId(id);

        return ProdutoAdapter.fromEntityToProdutoDTO(produtoRepository.save(entity));
    }

    @Transactional
    public void deletarProduto(Long id) {
        ProdutoEntity entity = ProdutoAdapter.fromDTOToProdutoEntity(this.buscarPorId(id));
        
        produtoRepository.delete(entity);
    }
}

