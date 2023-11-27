package com.example.backend.controller;

import com.example.backend.DTO.PedidoDTO;
import com.example.backend.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/api/salvar-dados")
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody  PedidoDTO pedidoDTO) {
        PedidoDTO pedidoCriado = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.ok().body(pedidoCriado);
    }

//    @PostMapping("/{clienteId}")
//    public ResponseEntity<PedidoDTO> criarPedido(@PathVariable Long clienteId, @RequestBody PedidoDTO pedidoDTO) {
//        PedidoDTO pedidoCriado = pedidoService.criarPedido(pedidoDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}


