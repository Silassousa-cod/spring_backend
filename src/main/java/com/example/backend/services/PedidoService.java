package com.example.backend.services;

import com.example.backend.DTO.PedidoDTO;
import com.example.backend.models.ClienteModel;
import com.example.backend.models.PedidoModel;
import com.example.backend.models.ProdutoModel;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.repository.EnderecoRepository;
import com.example.backend.repository.PedidoRepository;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        // Supondo que o ID do cliente esteja disponível no ClienteDTO
        Long clienteId = pedidoDTO.getCliente().getId(); // Substitua pelo método correto para obter o ID do cliente do ClienteDTO
    
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
    
        PedidoModel pedido = mapDTOToPedido(pedidoDTO);
        pedido.setCliente(cliente);
    
        calcularValorTotal(pedido);
    
        PedidoModel pedidoSalvo = pedidoRepository.save(pedido);
        return mapPedidoToDTO(pedidoSalvo);
    }
    

    private void calcularValorTotal(PedidoModel pedido) {
        List<ProdutoModel> produtos = produtoRepository.findAllById(pedido.getProdutos().stream().map(ProdutoModel::getId).collect(Collectors.toList()));

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoModel produto : produtos) {
            BigDecimal valorProduto = produto.getPreco().multiply(BigDecimal.valueOf(produto.getQuantidade()));
            valorTotal = valorTotal.add(valorProduto);
        }

        pedido.setValorTotal(valorTotal);
    }

    public PedidoDTO buscarPedidoPorId(Long id) {
        PedidoModel pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado"));
        return mapPedidoToDTO(pedido);
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO pedidoDTO) {
        PedidoModel pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado"));

        // Atualizar os campos do pedido com base no pedidoDTO recebido

        PedidoModel pedidoAtualizado = pedidoRepository.save(pedido);
        return mapPedidoToDTO(pedidoAtualizado);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO mapPedidoToDTO(PedidoModel pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setTaxaEntrega(pedido.getTaxaEntrega());
        // Mapear os demais atributos do pedido
        return pedidoDTO;
    }

    private PedidoModel mapDTOToPedido(PedidoDTO pedidoDTO) {
        PedidoModel pedido = new PedidoModel();
        pedido.setTaxaEntrega(pedidoDTO.getTaxaEntrega());
        // Mapear os demais atributos do pedido
        return pedido;
    }



}
