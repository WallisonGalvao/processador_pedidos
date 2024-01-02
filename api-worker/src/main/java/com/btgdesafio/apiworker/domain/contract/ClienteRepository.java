package com.btgdesafio.apiworker.domain.contract;

import com.btgdesafio.apiworker.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
