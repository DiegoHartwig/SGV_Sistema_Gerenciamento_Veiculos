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
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@Entity
@Table(name="veiculo")
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_veiculo")
	private int id;
	
	@Column(name="descricao")
	private String descricao;
		
	@Column(name="placa")
	private String placa;
	
	@Column(name = "data_cadastro")	
	private Calendar data_abertura = Calendar.getInstance();
	
	@Column(name="kminicial")
	private int kmInicial;
	
	@Column(name="kmatual")
	private int kmAtual;	
	
	@Column(name="centrocusto")
	private String centroCusto;
	
	@Column(name="combustivel")
	private String combustivel;

	
	// relacionamento Muitos para Um com modelo
	@ManyToOne
	@JoinColumn(name = "modelo_id")	
	private Modelo modelo;
	
	// relacionamento Muitos para Um com usuario
	@ManyToOne
	@JoinColumn(name = "usu_id")	
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Calendar getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Calendar data_abertura) {
		this.data_abertura = data_abertura;
	}

	public int getKmInicial() {
		return kmInicial;
	}

	public void setKmInicial(int kmInicial) {
		this.kmInicial = kmInicial;
	}

	public int getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(int kmAtual) {
		this.kmAtual = kmAtual;
	}	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
		
}
