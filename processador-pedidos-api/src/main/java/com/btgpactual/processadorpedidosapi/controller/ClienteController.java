package com.btgpactual.processadorpedidosapi.controller;

import com.btgpactual.processadorpedidosapi.service.RabbitMQService;
import constants.RabbitMQConstants;
import domain.contract.ClienteRepository;
import domain.entity.Cliente;
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
            if(repository.existsById(cliente.codCliente)) {
                return ResponseEntity.status(403).build();
            }
            else {
                service.enviaMensagem(RabbitMQConstants.FILA_ADD_CLIENTE, cliente);
                return ResponseEntity.status(201).body(cliente);
            }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        List listaClientes = repository.findAll();

        return listaClientes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaClientes);
    }


}
