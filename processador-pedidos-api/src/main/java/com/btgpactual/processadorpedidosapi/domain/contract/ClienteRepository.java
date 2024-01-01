package com.btgpactual.processadorpedidosapi.domain.contract;

import com.btgpactual.processadorpedidosapi.domain.entity.Cliente;
import com.btgpactual.processadorpedidosapi.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {






}
