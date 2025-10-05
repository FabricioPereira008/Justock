package com.justeam.justock_api.service;

import com.justeam.justock_api.model.finishOrder;
import com.justeam.justock_api.repository.finishOrderRepository;
import com.justeam.justock_api.request.finishOrderCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class finishOrderService {

    @Autowired
    private finishOrderRepository finishOrderRepository;

    public List<finishOrder> listAllofinishOrders() {
        return finishOrderRepository.findAll();
    }

    public finishOrder findofinishOrder(Long id) {
        return finishOrderRepository.findById(id).orElse(null);
    }

    public finishOrder createfinishOrder(finishOrderCreateRequest dto) {
        finishOrder finishOrder = new finishOrder();
        finishOrder.setOrder(dto.getOrder());
        finishOrder.setDataEntrega(dto.getDataEntrega());
        finishOrder.setDataEmissao(dto.getDataEmissao());
        finishOrder.setStatusPagamento(dto.getStatusPagamento());
        finishOrder.setStatusPedido(dto.getStatusPedido());
        return finishOrderRepository.save(finishOrder);
    }

    public boolean deletefinishOrder(Long id) {
        Optional<finishOrder> opt = finishOrderRepository.findById(id);
        if (opt.isEmpty()) return false;

        finishOrderRepository.delete(opt.get());
        return true;
    }
}
