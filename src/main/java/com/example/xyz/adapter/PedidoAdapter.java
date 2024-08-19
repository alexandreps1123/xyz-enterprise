package com.example.xyz.adapter;

import com.example.xyz.domain.Pedido;
import com.example.xyz.dto.PedidoDTO;
import com.example.xyz.entities.PedidoEntity;

import java.util.stream.Collectors;

public class PedidoAdapter {

    public static PedidoEntity toPedidoEntity(Pedido domain) {
        return PedidoEntity.builder()
            .dataPedido(domain.getDataPedido())
            .status(domain.getStatus())
            .itens(
                domain.getItens().stream()
                    .map(ItemPedidoAdapter::toItemEntity)
                    .collect(Collectors.toList()))
            .build();
    }

    public static PedidoEntity fromDTOToPedidoEntity(PedidoDTO dto) {
        return PedidoEntity.builder()
            .id(dto.getId())
            .dataPedido(dto.getDataPedido())
            .status(dto.getStatus())
            .itens(
                dto.getItens().stream()
                    .map(ItemPedidoAdapter::fromDTOToItemEntity)
                    .collect(Collectors.toList()))
            .build();
    }

    public static PedidoDTO fromEntityToPedidoDTO(PedidoEntity entity) {
        PedidoDTO Pedido = new PedidoDTO();
        Pedido.setId(entity.getId());
        Pedido.setDataPedido(entity.getDataPedido());
        Pedido.setStatus(entity.getStatus());
        Pedido.setItens(
            entity.getItens().stream()
            .map(ItemPedidoAdapter::fromEntityToItemDTO)
            .collect(Collectors.toList()));
            
        return Pedido;
    }

}
