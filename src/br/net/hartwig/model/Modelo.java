package br.net.hartwig.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name="modelo")
public class Modelo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_modelo")
	private int id;
		
	@Column(name="nome")
	private String nome;
	
	// relacionamento Muitos para Um com marca
	@ManyToOne
	@JoinColumn(name = "marca_id")	
	private Marca marca;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
		
}
