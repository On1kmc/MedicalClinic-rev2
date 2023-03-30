package com.ivanov.MedicalClinic.mapper;

import com.ivanov.MedicalClinic.dto.OrderDTO;
import com.ivanov.MedicalClinic.dto.OrderDTOToJSON;
import com.ivanov.MedicalClinic.model.Order;
import com.ivanov.MedicalClinic.model.Analyze;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = AnalyzeMapper.class, imports = {LocalDate.class})
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    default OrderDTOToJSON toJSONDTO(Order order) {
        OrderDTOToJSON orderDTOToJSON = new OrderDTOToJSON();
        orderDTOToJSON.setId(order.getId());
        List<Integer> idsList = order.getAnalyzeList().stream().map(Analyze::getId).toList();
        orderDTOToJSON.setAnalyzeIds(idsList);
        return orderDTOToJSON;
    }

    default List<OrderDTO> toListOrderDTO(List<Order> orderList) {
        return orderList.stream().map(this::toOrderDTO).collect(Collectors.toList());
    }

    Order toOrder(OrderDTO orderDTO);
}
