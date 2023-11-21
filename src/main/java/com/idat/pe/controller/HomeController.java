package com.idat.pe.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.idat.pe.model.DetallePedido;
import com.idat.pe.model.Pedido;
import com.idat.pe.model.Producto;
import com.idat.pe.model.Usuario;
import com.idat.pe.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

import com.idat.pe.service.IDetallePedidoService;
import com.idat.pe.service.IPedidoService;
import com.idat.pe.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IDetallePedidoService detallePedidoService;
	
	List<DetallePedido> detalles= new ArrayList<DetallePedido>();

	// datos de la pedido
	Pedido pedido= new Pedido();

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("name"));
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
		
		
	}
	
	@GetMapping("productoHome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id enviado como parametro {}",id);
		
		Producto producto = new Producto();
		Optional<Producto> productoOptional=productoService.get(id);
		producto = productoOptional.get();
		
		model.addAttribute("producto",producto);
		
		return "usuario/productoHome";	}
	
	@PostMapping("/carrito")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetallePedido detallePedido = new DetallePedido();
		Producto producto = new Producto();
		double sumaTotal =0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto=optionalProducto.get();
		
		detallePedido.setCantidad(cantidad);
		detallePedido.setPrecio(producto.getPrecio());
		detallePedido.setNombre(producto.getNombre());
		detallePedido.setTotal(producto.getPrecio()*cantidad);
		detallePedido.setProducto(producto);
		
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p->p.getProducto().getId()==idProducto);
		
		if(!ingresado) {
			detalles.add(detallePedido);
		}
		
		sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		DecimalFormat df = new DecimalFormat("#.00");
	    String formattedTotal = df.format(sumaTotal);

	    pedido.setTotal(Double.parseDouble(formattedTotal));
		model.addAttribute("carrito",detalles);
		model.addAttribute("pedido",pedido);
		

		return "usuario/carrito";
	}
	
	@GetMapping("/delete/carrito/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// lista nueva de prodcutos
		List<DetallePedido> pedidosNueva = new ArrayList<DetallePedido>();

		for (DetallePedido detallePedido : detalles) {
			if (detallePedido.getProducto().getId() != id) {
				pedidosNueva.add(detallePedido);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = pedidosNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		DecimalFormat df = new DecimalFormat("#.00");
	    String formattedTotal = df.format(sumaTotal);

	    pedido.setTotal(Double.parseDouble(formattedTotal));
		model.addAttribute("carrito", detalles);
		model.addAttribute("pedido", pedido);



		return "usuario/carrito";
	
	}
	
	@GetMapping("/obtenerCarrito")
	public String getCart(Model model) {
		
		model.addAttribute("carrito", detalles);
		model.addAttribute("pedido", pedido);
		
		return "/usuario/carrito";
	}
	
	@GetMapping("/pedido")
	public String order(Model model, HttpSession session) {
		
		Usuario usuario=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		
		model.addAttribute("carrito", detalles);
		model.addAttribute("pedido", pedido);
		model.addAttribute("usuario",usuario);
		
		return "usuario/resumenorden";
	}
	
	@GetMapping("/savePedido")
	public String savePedido(HttpSession session){
		
		Date fechaCreacion=new Date();
		pedido.setFechaCreacion(fechaCreacion);
		pedido.setNumero(pedidoService.generarNumeroPedido());
		
		Usuario usuario=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		pedido.setUsuario(usuario);
		pedidoService.save(pedido);
		
		for(DetallePedido dt:detalles) {
			dt.setPedido(pedido);
			detallePedidoService.save(dt);
		}
		
		pedido = new Pedido();
		detalles.clear();
		
		return"redirect:/";
	}
	
	@PostMapping("/buscar")
	public String buscarProduct(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto: {}", nombre);
		List<Producto> productos= productoService.findAll().stream().filter( p -> 
		p.getNombre().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("productos", productos);		
		return "usuario/home";
	}
	
}
