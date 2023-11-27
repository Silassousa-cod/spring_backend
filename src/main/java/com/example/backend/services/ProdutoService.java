package com.example.backend.services;

import com.example.backend.DTO.ProdutoDTO;
import com.example.backend.models.ProdutoModel;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> buscarTodosProdutos() {
        List<ProdutoModel> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::mapProdutoToDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        return mapProdutoToDTO(produto);
    }

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        ProdutoModel produto = mapDTOToProduto(produtoDTO);
        ProdutoModel produtoSalvo = produtoRepository.save(produto);
        return mapProdutoToDTO(produtoSalvo);
    }

    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

        produto.setNome(produtoDTO.getNome());
        produto.setImagem(produtoDTO.getImagem());
        produto.setPreco(produtoDTO.getPreco());

        ProdutoModel produtoAtualizado = produtoRepository.save(produto);
        return mapProdutoToDTO(produtoAtualizado);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO mapProdutoToDTO(ProdutoModel produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setImagem(produto.getImagem());
        produtoDTO.setPreco(produto.getPreco());
        return produtoDTO;
    }

    private ProdutoModel mapDTOToProduto(ProdutoDTO produtoDTO) {
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(produtoDTO.getNome());
        produto.setImagem(produtoDTO.getImagem());
        produto.setPreco(produtoDTO.getPreco());
        return produto;
    }
}

