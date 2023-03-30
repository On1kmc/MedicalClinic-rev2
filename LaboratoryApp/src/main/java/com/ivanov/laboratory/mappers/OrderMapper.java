package com.ivanov.laboratory.mappers;

import com.ivanov.laboratory.dto.OrderDTO;
import com.ivanov.laboratory.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface OrderMapper {

    @Mapping(source = "orderDTO.id", target = "clinicOrderId")
    Order toOrder(OrderDTO orderDTO);
}
