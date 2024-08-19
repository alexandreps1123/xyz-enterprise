package com.example.xyz.dto;

import lombok.Data;

@Data
public class ItemPedidoDTO {
    private Long id;
    private ProdutoDTO produto;
    private Integer quantidade;
}
