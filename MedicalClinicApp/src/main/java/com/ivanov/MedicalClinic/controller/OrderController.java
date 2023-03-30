package com.ivanov.MedicalClinic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanov.MedicalClinic.dto.AnalyzeDTO;
import com.ivanov.MedicalClinic.dto.TaskDTOForStatus;
import com.ivanov.MedicalClinic.model.Client;
import com.ivanov.MedicalClinic.model.Order;
import com.ivanov.MedicalClinic.model.Analyze;
import com.ivanov.MedicalClinic.services.AnalyzeService;
import com.ivanov.MedicalClinic.services.ClientService;
import com.ivanov.MedicalClinic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final AnalyzeService analyzeService;

    private final ClientService clientService;

    private final OrderService orderService;

    private ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;



    @Autowired
    public OrderController(AnalyzeService analyzeService, ClientService clientService, OrderService orderService, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.analyzeService = analyzeService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/new")
    public String newOrder(@RequestParam("id") int id, Model model) {
        model.addAttribute("person_id", id);
        List<AnalyzeDTO> analyzeDTOList = analyzeService.toDTOList(analyzeService.findAll());
        model.addAttribute("analyzes", analyzeDTOList);
        return "new-order";
    }

    @PostMapping("/new")
    public String addOrder(@RequestParam("analyze1") int id1, @RequestParam("analyze2") int id2,
                           @RequestParam("analyze3") int id3, @RequestParam("analyze4") int id4,
                           @RequestParam("analyze5") int id5, @RequestParam("client_id") int client_id) {
        Order order = new Order();
        List<Analyze> analyzes = analyzeService.getAnalyzesById(id1, id2, id3, id4, id5);
        order.setAnalyzeList(analyzes);
        Client client = clientService.getClientById(client_id);
        order.setClient(client);
        order.setDateOfOrder(LocalDate.now());
        orderService.saveOrder(order);
        String s;
        try {
            s = objectMapper.writeValueAsString(orderService.toOrderDTOToJSON(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send("topic1", s);          //////Отправляем в кафку!
        String redirectURL = String.valueOf(client.getId());

//        String url = "http://laboratory:8082/new-order";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> request = new HttpEntity<>(s, headers);
//        restTemplate.postForObject(url, request, String.class);

        return "redirect:/owner/" + redirectURL;
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable ("id") long id, Model model) throws JsonProcessingException {
        //Получить статусы для каждого анализа
        //Из заказа взять каждый анализ и установить статус
        String url = "http://laboratory:8082/new-order?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        ArrayList<TaskDTOForStatus> list = new ArrayList<>();
        JsonNode node = objectMapper.readTree(json);
        for (JsonNode s : node) {
            TaskDTOForStatus task = objectMapper.readValue(s.toString(), TaskDTOForStatus.class);
            list.add(task);
        }
        model.addAttribute("order", id);
        model.addAttribute("list", list);
        return "order-page";
    }



}
