package com.btgdesafio.apiworker.domain.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.btgdesafio.apiworker.domain.entity.Pedido;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId")
    Integer countPedidosPorCliente(@Param("clienteId") Long clienteId);
}
