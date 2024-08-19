package com.example.xyz.service;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.xyz.adapter.ItemPedidoAdapter;
import com.example.xyz.adapter.PedidoAdapter;
import com.example.xyz.config.RabbitMQConfig;
import com.example.xyz.domain.ItemPedido;
import com.example.xyz.domain.Pedido;
import com.example.xyz.dto.PedidoDTO;
import com.example.xyz.dto.ProdutoDTO;
import com.example.xyz.entities.PedidoEntity;
import com.example.xyz.enums.StatusPedido;
import com.example.xyz.repositories.PedidoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ProdutoService produtoService;
    private final PedidoRepository pedidoRepository;
    private final RabbitTemplate rabbitTemplate;

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll().stream()
            .map(PedidoAdapter::fromEntityToPedidoDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO buscarPorId(Long id) {
        return pedidoRepository.findById(id)
            .map(PedidoAdapter::fromEntityToPedidoDTO)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO dto) {

        List<ItemPedido> itens = dto.getItens().stream()
                                    .peek(item -> {
                                        ProdutoDTO produto = produtoService.buscarPorId(item.getProduto().getId());
                                        item.setProduto(produto);
                                    })
                                    .map(ItemPedidoAdapter::toDomain)
                                    .collect(Collectors.toList());

        System.out.println(itens);

        Pedido pedido = new Pedido(itens);

        PedidoEntity entity = pedidoRepository.save(PedidoAdapter.toPedidoEntity(pedido));
        return PedidoAdapter.fromEntityToPedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO atualizarPedido(Long id, PedidoDTO dto) {
        PedidoEntity entity = PedidoAdapter.fromDTOToPedidoEntity(buscarPorId(id));
        
        Pedido pedido = new Pedido(
            dto.getItens().stream()
                .map(ItemPedidoAdapter::toDomain)
                .collect(Collectors.toList())
        );

        entity = PedidoAdapter.toPedidoEntity(pedido);
        entity.setId(id);

        return PedidoAdapter.fromEntityToPedidoDTO(pedidoRepository.save(entity));
    }

    @Transactional
    public void deletarPedido(Long id) {
        PedidoEntity entity = PedidoAdapter.fromDTOToPedidoEntity(buscarPorId(id));

        pedidoRepository.delete(entity);
    }

    @Transactional
    public PedidoEntity alterarStatusPedido(Long id, StatusPedido novoStatus) {
        PedidoEntity pedido = PedidoAdapter.fromDTOToPedidoEntity(buscarPorId(id));

        pedido.setStatus(novoStatus);

        // Publica a mensagem no RabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                pedido
        );

        return pedido;
    }
    
}


