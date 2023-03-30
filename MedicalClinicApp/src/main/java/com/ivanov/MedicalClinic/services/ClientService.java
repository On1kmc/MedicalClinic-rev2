package com.ivanov.MedicalClinic.services;

import com.ivanov.MedicalClinic.Repo.ClientRepo;
import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.mapper.ClientListMapper;
import com.ivanov.MedicalClinic.mapper.ClientMapper;
import com.ivanov.MedicalClinic.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    private final ClientListMapper clientListMapper;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepo clientRepo, ClientListMapper clientListMapper, ClientMapper clientMapper) {
        this.clientRepo = clientRepo;
        this.clientListMapper = clientListMapper;
        this.clientMapper = clientMapper;
    }


    public Client findClientById(int id) {
        return clientRepo.findById(id).orElse(null);
    }

    public ClientDTO toClientDTO(Client client) {
        return clientMapper.toDTO(client);
    }


    public void saveOwner(Client client) {
        clientRepo.save(client);
    }


    public List<Client> loadClients() {
        return clientRepo.findAll();
    }


    public Client getClientById(int id) {
        return clientRepo.findById(id).get();
    }

    public List<ClientDTO> toClientDTOList(List<Client> clientList) {
        return clientListMapper.toDTOList(clientList);
    }

    public Client toClient(ClientDTO clientDTO) {
        return clientMapper.toModel(clientDTO);
    }
}
