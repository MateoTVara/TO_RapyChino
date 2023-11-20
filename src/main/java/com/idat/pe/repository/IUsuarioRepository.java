package com.idat.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.pe.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer>{

}
