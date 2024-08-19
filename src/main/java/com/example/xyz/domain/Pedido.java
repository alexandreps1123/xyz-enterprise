package com.example.xyz.domain;

import com.example.xyz.enums.StatusPedido;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private List<ItemPedido> itens;

    public Pedido(List<ItemPedido> itens) {
        setDataPedido(LocalDateTime.now());
        setStatus(StatusPedido.PENDENTE);
        setItens(itens);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    
}
