package com.example.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tb_cliente")
public class ClienteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "endereco_id", referencedColumnName = "id")
        private EnderecoModel endereco;

        @OneToMany(cascade = CascadeType.ALL)
        private List<ProdutoModel> produtos;




}
