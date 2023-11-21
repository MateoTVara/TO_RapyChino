package com.idat.pe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.pe.model.Pedido;
import com.idat.pe.model.Usuario;
import com.idat.pe.repository.IPedidoRepository;

@Service
public class PedidoServiceImpl implements IPedidoService{

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Override
	public Pedido save(Pedido pedido) {
		// TODO Auto-generated method stub
		return pedidoRepository.save(pedido);
	}

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public String generarNumeroPedido() {
		
		int numero=0;
		String numeroConcat="";
		
		List<Pedido> pedidos=findAll();
		
		List<Integer> numeros=new ArrayList<Integer>();
		
		pedidos.stream().forEach(o ->numeros.add(Integer.parseInt(o.getNumero())));
		
		if(pedidos.isEmpty()) {
			numero=1;
		}
		else {
			numero=numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if(numero<10) {
			numeroConcat="000000000"+String.valueOf(numero);
		}
		else if(numero<100) {
			numeroConcat="00000000"+String.valueOf(numero);
		}
		else if(numero<1000) {
			numeroConcat="0000000"+String.valueOf(numero);
		}
		else if(numero<10000) {
			numeroConcat="000000"+String.valueOf(numero);
		}
		
		return numeroConcat;
	}

	@Override
	public List<Pedido> findByUsuario(Usuario usuario) {
		return pedidoRepository.findByUsuario(usuario);
	}

}
