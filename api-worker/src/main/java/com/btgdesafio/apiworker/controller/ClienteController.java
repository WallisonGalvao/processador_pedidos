package com.btgdesafio.apiworker.controller;

import com.btgdesafio.apiworker.constants.RabbitMQConstants;
import com.btgdesafio.apiworker.domain.contract.ClienteRepository;
import com.btgdesafio.apiworker.domain.entity.Cliente;
import com.btgdesafio.apiworker.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository repository;
    @Autowired
    private RabbitMQService service;
    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
        if (repository.existsById(cliente.codCliente())) {
            return ResponseEntity.status(403).build(); // 403 - Forbidden
        } else {
            repository.save(cliente);
            service.enviaMensagem(RabbitMQConstants.FILA_ADD_CLIENTE, cliente);
            return ResponseEntity.status(201).body(cliente); //Created
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        List listaClientes = repository.findAll();

        if (listaClientes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        else {
            service.enviaMensagem(RabbitMQConstants.FILA_GET_CLIENTE, listaClientes);
            return ResponseEntity.status(200).body(listaClientes);
        }
    }




}
