package com.consumidor.consumidorbtg.consumer;

import constants.RabbitMQConstants;
import domain.entity.Pedido;
import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @RabbitListener(queues = RabbitMQConstants.FILA_ADD_PEDIDO)
    private void addPedidoConsumer(Message mensagem) {

    }
}
