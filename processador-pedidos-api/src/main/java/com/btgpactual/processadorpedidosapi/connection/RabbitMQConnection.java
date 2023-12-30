package com.btgpactual.processadorpedidosapi.connection;

import com.btgpactual.processadorpedidosapi.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConnection {
    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;
    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacao(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, trocaDireta().getName(), fila.getName(), null);
    }

    @PostConstruct //utilizado para executar esse método assim que a classe for construída
    private void add() {
        Queue filaPedido = this.fila(RabbitMQConstants.FILA_PEDIDO);

        DirectExchange troca = this.trocaDireta();

        Binding relacao = this.relacao(filaPedido, troca);

        //Criando as filas
        this.amqpAdmin.declareQueue(filaPedido);

        //Criando as exchanges
        this.amqpAdmin.declareExchange(troca);

        //Criando as relações
        this.amqpAdmin.declareBinding(relacao);
    }
}
