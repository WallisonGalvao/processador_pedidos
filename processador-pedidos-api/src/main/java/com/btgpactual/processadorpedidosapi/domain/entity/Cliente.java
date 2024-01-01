package com.btgpactual.processadorpedidosapi.domain.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Entity
public class Cliente implements Serializable {

    @Id
    public Integer codCliente;

    public Cliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getCodCliente() {
        return codCliente;
    }


}
