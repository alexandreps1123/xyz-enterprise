package com.example.xyz.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
}
