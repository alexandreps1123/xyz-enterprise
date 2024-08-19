package com.example.xyz.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.example.xyz.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pedido")
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private StatusPedido status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_pedido_id")
    private List<ItemPedidoEntity> itens;

}

