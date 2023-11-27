package com.example.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tb_produto")
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagem;
    private BigDecimal preco;
    private Integer quantidade;

    @ManyToMany
    @JoinTable(
            name = "Tb_pro_ped",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "pedido_id")
    )
    Set<PedidoModel> pedidoModelSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;
}
