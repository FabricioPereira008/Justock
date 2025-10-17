package com.justeam.justock_api.controller;

import com.justeam.justock_api.dto.apiResponseDTO;
import com.justeam.justock_api.dto.orderResponseDTO;
import com.justeam.justock_api.model.order;
import com.justeam.justock_api.request.orderCreateRequest;
import com.justeam.justock_api.service.orderService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class orderController {

    @Autowired
    private orderService orderService;

    @GetMapping
    public apiResponseDTO<List<orderResponseDTO>> index() {
        List<orderResponseDTO> orders = orderService.listAlloOrders()
            .stream()
            .map(o -> new orderResponseDTO(o.getId(), o.getQuantidade(), o.getPrecoUnitario(), o.getSubtotal(), o.getIdItemMarketplace(), o.getStatus()))
            .collect(Collectors.toList());

        return new apiResponseDTO<>(200, "Pedidos encontrados!!", orders);
    }

    @GetMapping("/{id}")
    public apiResponseDTO<orderResponseDTO> show(@PathVariable Long id) {
        order o = orderService.findoOrder(id);
        if (o == null) return new apiResponseDTO<>(404, "Produto não encontrado!", null);

        return new apiResponseDTO<>(200, "Pedido encontrado!!",
                new orderResponseDTO(o.getId(), o.getQuantidade(), o.getPrecoUnitario(), o.getSubtotal(), o.getIdItemMarketplace(), o.getStatus()));
    }
    
}
