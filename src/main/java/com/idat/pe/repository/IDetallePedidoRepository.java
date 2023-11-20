package com.idat.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.pe.model.DetallePedido;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{

}
