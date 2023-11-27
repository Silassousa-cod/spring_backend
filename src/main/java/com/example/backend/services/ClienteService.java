package com.example.backend.services;

import com.example.backend.DTO.ClienteDTO;
import com.example.backend.models.ClienteModel;
import com.example.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> buscarTodosClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::mapToClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarClientePorId(Long id) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
        return mapToClienteDTO(cliente);
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        ClienteModel cliente = mapToClienteModel(clienteDTO);
        ClienteModel clienteSalvo = clienteRepository.save(cliente);
        return mapToClienteDTO(clienteSalvo);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {

            ClienteModel cliente = clienteRepository.getReferenceById(id);
            cliente.setNome(clienteDTO.getNome());
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente = clienteRepository.save(cliente);
            return new ClienteDTO(cliente);

    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO mapToClienteDTO(ClienteModel clienteModel) {
        ClienteDTO clienteDTO = new ClienteDTO();
        // Mapear os campos do clienteModel para o clienteDTO
        clienteDTO.setId(clienteModel.getId());
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setTelefone(clienteModel.getTelefone());
        // Mapear outros campos...

        return clienteDTO;
    }

    private ClienteModel mapToClienteModel(ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        // Mapear os campos do clienteDTO para o clienteModel
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setTelefone(clienteDTO.getTelefone());
        // Mapear outros campos...

        return clienteModel;
    }
}
