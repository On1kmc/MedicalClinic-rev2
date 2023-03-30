package com.ivanov.laboratory.services;

import com.ivanov.laboratory.Repo.OrderRepo;
import com.ivanov.laboratory.dto.OrderDTO;
import com.ivanov.laboratory.mappers.OrderMapper;
import com.ivanov.laboratory.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final TaskService taskService;

    private final AnalyzeService analyzeService;

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepo orderRepo, TaskService taskService, AnalyzeService analyzeService, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.taskService = taskService;
        this.analyzeService = analyzeService;
        this.orderMapper = orderMapper;
    }


    public void saveOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toOrder(orderDTO);
        order.setAnalyzeList(analyzeService.getAnalyzesById(orderDTO.getAnalyzeIds()));
        Order order1 = orderRepo.save(order);
        taskService.createTaskListToOrder(order1);
    }



    public Order findByOrderId(long id) {
       return orderRepo.findByClinicOrderId(id);
    }
}
