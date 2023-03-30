package com.ivanov.MedicalClinic.mapper;

import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface ClientListMapper {

    List<ClientDTO> toDTOList(List<Client> clientList);

}
