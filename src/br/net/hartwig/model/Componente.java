package br.net.hartwig.model;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 * Os componentes são as peças do veículo ex: pneu, kit transmissão
 */
@Entity
@Table(name="componente")
public class Componente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_componente")
	private int id;
		
	@Column(name="nome")
	private String nome;	
	
	@Column(name = "datacadastro")	
	private Calendar dataCadastro = Calendar.getInstance();	
	
	@Column(name="hodometro")
	private int hodometro;
	
	@Column(name="status")
	private String status;	

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

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}	

	public int getHodometro() {
		return hodometro;
	}

	public void setHodometro(int hodometro) {
		this.hodometro = hodometro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	
		
}
