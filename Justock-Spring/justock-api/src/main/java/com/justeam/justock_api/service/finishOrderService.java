package com.justeam.justock_api.service;

import com.justeam.justock_api.model.FinishOrder;
import com.justeam.justock_api.repository.FinishOrderRepository;
import com.justeam.justock_api.request.FinishOrderCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class FinishOrderService {

    @Autowired
    private FinishOrderRepository finishOrderRepository;

    public List<FinishOrder> listAllofinishOrders() {
        return finishOrderRepository.findAll();
    }

    public FinishOrder findofinishOrder(Long id) {
        return finishOrderRepository.findById(id).orElse(null);
    }

    public FinishOrder createfinishOrder(FinishOrderCreateRequest dto) {
        FinishOrder finishOrder = new FinishOrder();
        finishOrder.setOrder(dto.getOrder());
        finishOrder.setDataEntrega(dto.getDataEntrega());
        finishOrder.setDataEmissao(dto.getDataEmissao());
        finishOrder.setStatusPagamento(dto.getStatusPagamento());
        finishOrder.setStatusPedido(dto.getStatusPedido());
        return finishOrderRepository.save(finishOrder);
    }

    public boolean deletefinishOrder(Long id) {
        Optional<FinishOrder> opt = finishOrderRepository.findById(id);
        if (opt.isEmpty()) return false;

        finishOrderRepository.delete(opt.get());
        return true;
    }
}
