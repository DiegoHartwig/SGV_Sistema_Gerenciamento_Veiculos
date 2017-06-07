
package br.net.hartwig.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.net.hartwig.dao.ModeloDAO;
import br.net.hartwig.dao.VeiculoDAO;
import br.net.hartwig.model.Modelo;
import br.net.hartwig.model.Veiculo;
/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@SessionScoped
@ManagedBean(name = "veiculoBean")

public class VeiculoBean implements Serializable{

	private static final long serialVersionUID = 9156359910559743426L;

	private Veiculo veiculo = new Veiculo();
	
	private VeiculoDAO veiculoDAO = new VeiculoDAO();

	private DataModel<Veiculo> veiculos;
	
	private int modelo_id;
	
	private int usuario_id;
	
	//Variável utilizada no DataTable para filtrar veiculos
	private List<Veiculo> veiculosFiltrados;
	
	//Variável que armazena o veiculo selecionado
	private Veiculo veiculoSelecionado;	
	

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public VeiculoDAO getVeiculoDAO() {
		return veiculoDAO;
	}

	public void setVeiculoDAO(VeiculoDAO veiculoDAO) {
		this.veiculoDAO = veiculoDAO;
	}

	public Veiculo getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}

	public void setVeiculos(DataModel<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}	

	public int getModelo_id() {
		return modelo_id;
	}

	public void setModelo_id(int modelo_id) {
		this.modelo_id = modelo_id;
	}	

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	

	public List<Veiculo> getVeiculosFiltrados() {
		return veiculosFiltrados;
	}

	public void setVeiculosFiltrados(List<Veiculo> veiculosFiltrados) {
		this.veiculosFiltrados = veiculosFiltrados;
	}

	public void selecionarVeiculo() {
		this.veiculo = veiculos.getRowData();
	}

	public DataModel<Veiculo> getVeiculos() {
		VeiculoDAO dao = new VeiculoDAO();

		try {
			List<Veiculo> lista = dao.GetALL();
			veiculos = new ListDataModel<Veiculo>(lista);
		} catch (Exception e) {

		}

		return veiculos;
	}

	// delete 
	public String deleteVeiculo() {
		this.veiculo = veiculos.getRowData();

		String retorno = "erro";

		try {
			VeiculoDAO dao = new VeiculoDAO();
			
			dao.Delete(veiculo);
			
			String placa = veiculo.getPlaca();

			// Mensagem de confirmação após deletar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo: "+placa+", excluído do Sistema"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updateVeiculo() {

		String retorno = "erro";

		try {
			VeiculoDAO dao = new VeiculoDAO();				
			
			dao.Update(veiculo);
			
			String placa = veiculo.getPlaca();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo, placa: "+placa+",atualizado com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novoVeiculo() {
		veiculo = new Veiculo();
	}

	public String addVeiculo() {

		String retorno = "erro";

		try {
			VeiculoDAO dao = new VeiculoDAO();
			
			ModeloDAO modeloDao = new ModeloDAO();
			
			veiculo.setModelo(modeloDao.Get(modelo_id));

			String placa = veiculo.getPlaca();
			
			String modeloVeiculo = veiculo.getModelo().getNome();			
			
			dao.Salvar(veiculo);			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo - modelo: "+modeloVeiculo+", placa: "+placa+", cadastrado com sucesso!"));

			retorno = "veiculos";
		} catch (Exception ex) {

		}
		return retorno;

	}
	

	public Collection<SelectItem> getCarregarModelos() {
		ModeloDAO dao = new ModeloDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione o modelo --"));
		List<Modelo> listaModelo = dao.GetALL();

		for (int i = 0; i < listaModelo.size(); i++) {
			lista.add(new SelectItem(listaModelo.get(i).getId(), listaModelo.get(i).getNome()));
		}

		return lista;
	}

}
