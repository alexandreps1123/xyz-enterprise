package com.example.xyz.adapter;

import com.example.xyz.domain.Produto;
import com.example.xyz.dto.ProdutoDTO;
import com.example.xyz.entities.ProdutoEntity;

public class ProdutoAdapter {

    public static Produto toDomain(ProdutoDTO dto) {
        Produto produto = new Produto();

        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        return produto;
    }

    public static ProdutoEntity toProdutoEntity(Produto domain) {
        return ProdutoEntity.builder()
            .nome(domain.getNome())
            .descricao(domain.getDescricao())
            .preco(domain.getPreco())
            .quantidade(domain.getQuantidade())
            .build();
    }

    public static ProdutoEntity fromDTOToProdutoEntity(ProdutoDTO dto) {
        return ProdutoEntity.builder()
            .id(dto.getId())
            .nome(dto.getNome())
            .descricao(dto.getDescricao())
            .preco(dto.getPreco())
            .quantidade(dto.getQuantidade())
            .build();
    }

    public static ProdutoDTO fromEntityToProdutoDTO(ProdutoEntity entity) {
        ProdutoDTO produto = new ProdutoDTO();

        produto.setId(entity.getId());
        produto.setNome(entity.getNome());
        produto.setDescricao(entity.getDescricao());
        produto.setPreco(entity.getPreco());
        produto.setQuantidade(entity.getQuantidade());

        return produto;
    }

}
