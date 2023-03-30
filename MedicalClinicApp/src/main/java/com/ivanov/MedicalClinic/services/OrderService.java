package com.ivanov.MedicalClinic.services;

import com.ivanov.MedicalClinic.Repo.OrderRepo;
import com.ivanov.MedicalClinic.dto.OrderDTO;
import com.ivanov.MedicalClinic.dto.OrderDTOToJSON;
import com.ivanov.MedicalClinic.mapper.OrderMapper;
import com.ivanov.MedicalClinic.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final OrderMapper orderMapper;

    private final ClientService clientService;

    @Autowired
    public OrderService(OrderRepo orderRepo, OrderMapper orderMapper, ClientService clientService) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.clientService = clientService;
    }


    public List<OrderDTO> getOrderListForClient(int id) {
        List<Order> allByClientId = clientService.getClientById(id).getOrderList();
        return orderMapper.toListOrderDTO(allByClientId);
    }
    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    public void saveOrder(OrderDTO orderDTO) {
        orderRepo.save(orderMapper.toOrder(orderDTO));
    }

    public OrderDTOToJSON toOrderDTOToJSON(Order order) {
        return orderMapper.toJSONDTO(order);
    }

}
