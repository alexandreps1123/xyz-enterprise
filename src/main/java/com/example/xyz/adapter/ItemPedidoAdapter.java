package com.example.xyz.adapter;

import com.example.xyz.domain.ItemPedido;
import com.example.xyz.dto.ItemPedidoDTO;
import com.example.xyz.entities.ItemPedidoEntity;

public class ItemPedidoAdapter {

    public static ItemPedido toDomain(ItemPedidoDTO dto) {
        ItemPedido itemPedido = new ItemPedido();
    
        itemPedido.setProduto(ProdutoAdapter.toDomain(dto.getProduto()));
        itemPedido.setQuantidade(dto.getQuantidade());

        return itemPedido;
    }

    public static ItemPedidoEntity toItemEntity(ItemPedido domain) {
        return ItemPedidoEntity.builder()
            .produto(ProdutoAdapter.toProdutoEntity(domain.getProduto()))
            .quantidade(domain.getQuantidade())
            .build();
    }

    public static ItemPedidoEntity fromDTOToItemEntity(ItemPedidoDTO dto) {
        return ItemPedidoEntity.builder()
            .id(dto.getId())
            .produto(ProdutoAdapter.fromDTOToProdutoEntity(dto.getProduto()))
            .quantidade(dto.getQuantidade())
            .build();
    }

    public static ItemPedidoDTO fromEntityToItemDTO(ItemPedidoEntity entity) {
        ItemPedidoDTO ItemPedido = new ItemPedidoDTO();
        ItemPedido.setId(entity.getId());
        ItemPedido.setProduto(ProdutoAdapter.fromEntityToProdutoDTO(entity.getProduto()));
        ItemPedido.setQuantidade(entity.getQuantidade());
        return ItemPedido;
    }

}
