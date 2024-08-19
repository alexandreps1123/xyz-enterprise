package com.example.xyz.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.xyz.dto.PedidoDTO;
import com.example.xyz.entities.PedidoEntity;
import com.example.xyz.enums.StatusPedido;
import com.example.xyz.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        return ResponseEntity.ok().body(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPedido(@PathVariable Long id) {
        try {
            PedidoDTO dto = pedidoService.buscarPorId(id);               
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody PedidoDTO pedido) {
        PedidoDTO dto = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedido) {
        PedidoDTO dto =  pedidoService.atualizarPedido(id, pedido);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/status")
    public PedidoEntity alterarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        return pedidoService.alterarStatusPedido(id, status);
    }
    
}

