package com.idat.pe.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name ="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;

	private double total;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido")
	private List<DetallePedido> detalle;
	
	public Pedido() {
		super();
	}

	public Pedido(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total, Usuario usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return fechaRecibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetallePedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallePedido> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + "]";
	}
	
	
}
