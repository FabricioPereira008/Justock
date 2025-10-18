package com.justeam.justock_api.controller;

import com.justeam.justock_api.dto.ApiResponseDTO;
import com.justeam.justock_api.dto.OrderResponseDTO;
import com.justeam.justock_api.model.Order;
import com.justeam.justock_api.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ApiResponseDTO<List<OrderResponseDTO>> index() {
        List<OrderResponseDTO> orders = orderService.listAlloOrders()
            .stream()
            .map(o -> new OrderResponseDTO(o.getId(), o.getQuantidade(), o.getPrecoUnitario(), o.getSubtotal(), o.getIdItemMarketplace(), o.getStatus()))
            .collect(Collectors.toList());

        return new ApiResponseDTO<>(200, "Pedidos encontrados!!", orders);
    }

    @GetMapping("/{id}")
    public ApiResponseDTO<OrderResponseDTO> show(@PathVariable Long id) {
        Order o = orderService.findoOrder(id);
        if (o == null) return new ApiResponseDTO<>(404, "Produto não encontrado!", null);

        return new ApiResponseDTO<>(200, "Pedido encontrado!!",
                new OrderResponseDTO(o.getId(), o.getQuantidade(), o.getPrecoUnitario(), o.getSubtotal(), o.getIdItemMarketplace(), o.getStatus()));
    }
    
}
