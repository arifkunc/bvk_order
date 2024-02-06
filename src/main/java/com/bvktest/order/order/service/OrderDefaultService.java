package com.bvktest.order.order.service;

import com.bvktest.order.order.object.OrderProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderDefaultService implements OrderService{
    private RestTemplate restTemplate;

    @Autowired
    public OrderDefaultService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void orderProduct(OrderProductDto orderProductDto) {
        // trigger update quantity to Inventory microservice

        // insert new order into database
    }
}
