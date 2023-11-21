package com.idat.pe.service;

import java.util.List;

import com.idat.pe.model.Pedido;
import com.idat.pe.model.Usuario;

public interface IPedidoService {
	
	Pedido save(Pedido pedido);
	List<Pedido> findAll();
	String generarNumeroPedido();
	List<Pedido> findByUsuario(Usuario usuario);
}
