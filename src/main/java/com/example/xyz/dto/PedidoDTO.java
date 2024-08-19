package com.example.xyz.dto;

import com.example.xyz.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private List<ItemPedidoDTO> itens;
}
