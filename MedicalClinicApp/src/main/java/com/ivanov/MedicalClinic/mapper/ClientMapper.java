package com.ivanov.MedicalClinic.mapper;

import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO(Client client);

    Client toModel(ClientDTO clientDTO);
}
