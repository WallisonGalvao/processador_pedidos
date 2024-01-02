package com.btgdesafio.apiworker.connection.rabbitMQ;

import com.btgdesafio.apiworker.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    //Criação da exchange
    public static final String BTG_EXCHANGE = "btg-exchange";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return  new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return  new DirectExchange(BTG_EXCHANGE);
    }

    private Binding relacao(Queue fila, DirectExchange troca) {
        return  new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct //utilizado para executar esse método assim que a classe for construída
    private void add() {
        Queue filaAddCliente = this.fila(RabbitMQConstants.FILA_ADD_CLIENTE);
        Queue filaAddPedido = this.fila(RabbitMQConstants.FILA_ADD_PEDIDO);
        Queue filaGetCliente = this.fila(RabbitMQConstants.FILA_GET_CLIENTE);
        Queue filaGetPedido = this.fila(RabbitMQConstants.FILA_GET_PEDIDO);
        Queue filaDelCliente = this.fila(RabbitMQConstants.FILA_DEL_CLIENTE);

        DirectExchange troca = this.trocaDireta();

        Binding relacaoAddPedido = this.relacao(filaAddPedido, troca);
        Binding relacaoGetPedido = this.relacao(filaGetPedido, troca);
        Binding relacaoAddCliente = this.relacao(filaAddCliente, troca);
        Binding relacaoGetCliente = this.relacao(filaGetCliente, troca);
        Binding relacaoDelCliente = this.relacao(filaDelCliente, troca);

        //Criando as filas
        this.amqpAdmin.declareQueue(filaAddPedido);
        this.amqpAdmin.declareQueue(filaGetPedido);
        this.amqpAdmin.declareQueue(filaAddCliente);
        this.amqpAdmin.declareQueue(filaGetCliente);
        this.amqpAdmin.declareQueue(filaDelCliente);

        //Criando as exchanges
        this.amqpAdmin.declareExchange(troca);

        //Criando as relações
        this.amqpAdmin.declareBinding(relacaoAddPedido);
        this.amqpAdmin.declareBinding(relacaoGetPedido);
        this.amqpAdmin.declareBinding(relacaoAddCliente);
        this.amqpAdmin.declareBinding(relacaoGetCliente);
        this.amqpAdmin.declareBinding(relacaoDelCliente);

    }
}
