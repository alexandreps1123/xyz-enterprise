package com.example.xyz.domain;

import java.math.BigDecimal;

import com.example.xyz.exceptions.BusinessLogicException;

public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidade) {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setQuantidade(quantidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        if(preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessLogicException("Nao eh permitido preco menor que zero.");
        }

        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        if(quantidade <= 0) {
            throw new BusinessLogicException("Nao eh permitido quantidade zero ou menor.");
        }

        this.quantidade = quantidade;
    }

}
