package com.idat.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.pe.model.Pedido;
import com.idat.pe.model.Usuario;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByUsuario(Usuario usuario);
	
}
