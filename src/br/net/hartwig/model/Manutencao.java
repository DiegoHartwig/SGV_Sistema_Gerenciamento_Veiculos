package br.net.hartwig.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name = "manutencao")
public class Manutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "datamanutencao")	
	private Calendar dataManutencao = Calendar.getInstance();

	@Column(name = "datavcto")
	private Calendar dataVcto;	

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "status")
	private String status;


	// relacionamento Muitos para Um com veiculo
	@ManyToOne
	@JoinColumn(name = "veiculo_id")	
	private Veiculo veiculo;
	
	//relacionamento Muitos para Um com componente
	@ManyToOne
	@JoinColumn(name = "componente_id")
	private Componente componente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Calendar dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public Calendar getDataVcto() {
		return dataVcto;
	}

	public void setDataVcto(Calendar dataVcto) {
		this.dataVcto = dataVcto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	
	


	
}
