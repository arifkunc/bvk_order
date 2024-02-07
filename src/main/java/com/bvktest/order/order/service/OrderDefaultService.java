package com.bvktest.order.order.service;

import com.bvktest.order.order.client.object.UpdateProductQuantityRequest;
import com.bvktest.order.order.client.object.UpdateProductQuantityResponse;
import com.bvktest.order.order.object.OrderProductDto;
import com.bvktest.order.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class OrderDefaultService implements OrderService{
    private String apiUrlUpdateQuantity;
    private RestTemplate restTemplate;
    private OrderRepository orderRepository;

    @Autowired
    public OrderDefaultService(@Value("${external-api.inventory.url.update-quantity}") String apiUrlUpdateQuantity,
                               RestTemplateBuilder restTemplateBuilder,
                               OrderRepository orderRepository) {
        this.apiUrlUpdateQuantity = apiUrlUpdateQuantity;
        this.restTemplate = restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build();
        this.orderRepository = orderRepository;
    }

    @Override
    public String orderProduct(OrderProductDto orderProductDto) {
        // trigger update quantity to Inventory Microservice
        int quantityDelta = -orderProductDto.getQuantity();

        UpdateProductQuantityRequest updateProductQuantityRequest = UpdateProductQuantityRequest.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli()))
                .quantityDelta(quantityDelta)
                .build();

        UpdateProductQuantityResponse updateProductQuantityResponse = restTemplate.patchForObject(apiUrlUpdateQuantity,
                updateProductQuantityRequest,
                UpdateProductQuantityResponse.class,
                orderProductDto.getProductId());

        // insert new order into database
        return orderRepository.insertOrder(orderProductDto);
    }
}
