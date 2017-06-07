package br.net.hartwig.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name="marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_marca")
	private int id;
		
	@Column(name="nome")
	private String nome;

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
	
		
}
