package com.ivanov.laboratory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivanov.laboratory.dto.OrderDTO;
import com.ivanov.laboratory.dto.TaskDTOForStatus;
import com.ivanov.laboratory.models.Order;
import com.ivanov.laboratory.services.OrderService;
import com.ivanov.laboratory.services.ResponseService;
import com.ivanov.laboratory.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/new-order")
public class OrderController {


    private final OrderService orderService;

    private final ResponseService responseService;

    private final TaskService taskService;

    @Autowired
    public OrderController(OrderService orderService, ResponseService responseService, TaskService taskService) {
        this.orderService = orderService;
        this.responseService = responseService;
        this.taskService = taskService;
    }

//    @PostMapping()
//    public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderDTO order, BindingResult bindingResult) {
//        orderService.saveOrder(order);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    @GetMapping
    public HttpEntity<Object> getStatuses(@RequestParam long id) throws JsonProcessingException {
        Order order = orderService.findByOrderId(id);
        List<TaskDTOForStatus> taskDTOListForResponse = taskService.getTaskDTOListForResponse(order);
        return responseService.getResponse(taskDTOListForResponse);
    }

}
