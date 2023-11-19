package com.idat.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.idat.pe.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ProductoService productoService;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}
	
}