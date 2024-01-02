package com.btgdesafio.apiworker.service;

import com.btgdesafio.apiworker.domain.contract.PedidoRepository;
import com.btgdesafio.apiworker.domain.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void addPedido(Pedido pedido) {

    }

    public Map<Long, Integer> countPedidosByCliente() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        Map<Long, Integer> countMap = new HashMap<>();
        for (Pedido pedido : pedidos) {
            long codigoCliente = pedido.getIdPedido();
            countMap.put(codigoCliente, countMap.getOrDefault(codigoCliente, 0) + 1);
        }

        return countMap;
    }
}
