package com.idat.pe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.pe.model.DetallePedido;
import com.idat.pe.repository.IDetallePedidoRepository;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService{

	@Autowired
	private IDetallePedidoRepository detallePedidoRepository;
	
	@Override
	public DetallePedido save(DetallePedido detallePedido) {
		return detallePedidoRepository.save(detallePedido);
	}

}
