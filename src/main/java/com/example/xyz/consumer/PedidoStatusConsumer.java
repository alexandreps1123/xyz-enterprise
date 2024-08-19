package com.example.xyz.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.xyz.config.RabbitMQConfig;
import com.example.xyz.entities.PedidoEntity;
import com.example.xyz.repositories.PedidoRepository;

@Component
@RequiredArgsConstructor
public class PedidoStatusConsumer {

    private final PedidoRepository pedidoRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumirMensagem(PedidoEntity pedido) {
        // Atualiza o status do pedido no banco de dados
        PedidoEntity pedidoExistente = pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + pedido.getId()));
        
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoRepository.save(pedidoExistente);
    }
}

