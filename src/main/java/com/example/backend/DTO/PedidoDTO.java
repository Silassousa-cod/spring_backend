package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private LocalDate dataePedido;
    private ClienteDTO cliente;
    private CarrinhoDTO carrinho;
    private List<ProdutoDTO> produtos;
    private BigDecimal taxaEntrega;
    private EnderecoDTO enderecoEntrega;
}
