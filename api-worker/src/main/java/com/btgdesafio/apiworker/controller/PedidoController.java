package com.btgdesafio.apiworker.controller;

import com.btgdesafio.apiworker.constants.RabbitMQConstants;
import com.btgdesafio.apiworker.domain.contract.PedidoRepository;
import com.btgdesafio.apiworker.domain.entity.Pedido;
import com.btgdesafio.apiworker.service.PedidoService;
import com.btgdesafio.apiworker.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    RabbitMQService service;



    @Autowired
    PedidoRepository repository;

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido) {

        if (repository.existsById(pedido.getIdPedido())) {
            return ResponseEntity.status(403).build();
        }
        else {
            service.enviaMensagem(RabbitMQConstants.FILA_ADD_PEDIDO, pedido);
            repository.save(pedido);
            return ResponseEntity.status(201).body(pedido);
        }
    }

    @GetMapping("/pedidos-por-cliente/{id}")
    public ResponseEntity<Map<Long, Integer>> countPedidosByCliente(@PathVariable Long id) {
        service.enviaMensagem(RabbitMQConstants.FILA_GET_PEDIDO, id);
        Map<Long, Integer> countMap = pedidoService.countPedidosByCliente();
        return ResponseEntity.ok(countMap);
    }

}
