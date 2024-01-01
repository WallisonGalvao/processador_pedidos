package com.btgpactual.processadorpedidosapi.domain.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Item {
    @Id
    public String produto;

    public Integer quantidade;

    public Double preco;

    public Item(String produto, Integer quantidade, Double preco) {
        this.produto = produto;
        this.quantidade = 0;
        this.preco = 0.0;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }


    public Double getPreco() {
        return preco;
    }


}
