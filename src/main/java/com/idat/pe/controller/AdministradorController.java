package com.idat.pe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idat.pe.model.Pedido;
import com.idat.pe.model.Producto;
import com.idat.pe.service.IPedidoService;
import com.idat.pe.service.IProductoService;
import com.idat.pe.service.IUsuarioService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPedidoService pedidoService;

	@GetMapping("")
	public String home(Model model) {

		List<Producto> productos=productoService.findAll();
		model.addAttribute("productos", productos);

		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuario(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/pedidos")
	public String pedidos(Model model) {
		model.addAttribute("pedidos",pedidoService.findAll());
		return "administrador/pedidos";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		
		Pedido pedido = pedidoService.findById(id).get();
		
		model.addAttribute("detalles", pedido.getDetalle());
		
		return "administrador/detallePedido";
	}
	
}
