package com.idat.pe.service;

import java.util.List;
import java.util.Optional;

import com.idat.pe.model.Pedido;
import com.idat.pe.model.Usuario;

public interface IPedidoService {
	
	Pedido save(Pedido pedido);
	Optional<Pedido> findById(Integer id);
	List<Pedido> findAll();
	String generarNumeroPedido();
	List<Pedido> findByUsuario(Usuario usuario);
}
