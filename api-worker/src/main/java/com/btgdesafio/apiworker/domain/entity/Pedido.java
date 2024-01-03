package com.btgdesafio.apiworker.domain.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name= "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itens;

    public Pedido(Long idPedido, Cliente cliente, List<Item> itens) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.itens = itens;
    }

    public Pedido() {}

    public Long getIdPedido() {
        return idPedido;
    }



    public List<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public Double precoTotal() {
        Double total = 0.0;
                for (Item item: itens) {
                    total += (item.getValor() * item.getQuantidade());
                }
                return total;
    }
}
