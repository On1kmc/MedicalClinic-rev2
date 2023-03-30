package com.ivanov.laboratory.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanov.laboratory.dto.OrderDTO;
import com.ivanov.laboratory.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderListener {

    private final OrderService orderService;

    @Autowired
    public KafkaOrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(id = "lab", topics = "topic1")
    public void listen(String in) {
        ObjectMapper mapper = new ObjectMapper();
        OrderDTO order = null;
        try {
            order = mapper.readValue(in, OrderDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        orderService.saveOrder(order);
    }
}
