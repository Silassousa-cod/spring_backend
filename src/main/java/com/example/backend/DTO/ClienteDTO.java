package com.example.backend.DTO;

import com.example.backend.models.ClienteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private Long endereco_id;
    private Integer quantidade;

    public ClienteDTO (ClienteModel cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
    }
}
