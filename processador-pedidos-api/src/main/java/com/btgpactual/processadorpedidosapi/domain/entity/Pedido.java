package com.btgpactual.processadorpedidosapi.domain.entity;


import com.btgpactual.processadorpedidosapi.domain.entity.Item;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Entity
public class Pedido{

    @Id
    public Integer codPedido;
    public Integer codCliente;

    public ArrayList<Item> itens;

    public Pedido(Integer codPedido, Integer codCliente, ArrayList<Item> itens) {
        this.codPedido = codPedido;
        this.codCliente = codCliente;
        this.itens = new ArrayList<>();
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }
}
