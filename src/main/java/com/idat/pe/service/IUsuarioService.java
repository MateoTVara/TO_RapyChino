package com.idat.pe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.idat.pe.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> findById(Integer id);
}
