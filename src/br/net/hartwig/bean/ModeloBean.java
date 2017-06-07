
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

import br.net.hartwig.dao.MarcaDAO;
import br.net.hartwig.dao.ModeloDAO;
import br.net.hartwig.model.Marca;
import br.net.hartwig.model.Modelo;
/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@SessionScoped
@ManagedBean(name = "modeloBean")

public class ModeloBean implements Serializable{
	

	private static final long serialVersionUID = -2925632992964596486L;

	private Modelo modelo = new Modelo();
	
	private ModeloDAO marcaDAO = new ModeloDAO();

	private DataModel<Modelo> modelos;
	
	private int marca_id;
	
	//Variável que armazena o modelo selecionado no dataTable
	private Modelo modeloSelecionado;	
	

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public ModeloDAO getMarcaDAO() {
		return marcaDAO;
	}

	public void setMarcaDAO(ModeloDAO marcaDAO) {
		this.marcaDAO = marcaDAO;
	}

	public DataModel<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(DataModel<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(Modelo modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}	

	public int getMarca_id() {
		return marca_id;
	}

	public void setMarca_id(int marca_id) {
		this.marca_id = marca_id;
	}

	public void selecionarMarca() {
		this.modelo = modelos.getRowData();
	}

	public DataModel<Modelo> getMarcas() {
		ModeloDAO dao = new ModeloDAO();

		try {
			List<Modelo> lista = dao.GetALL();
			modelos = new ListDataModel<Modelo>(lista);
		} catch (Exception e) {

		}

		return modelos;
	}

	// delete 
	public String deleteModelo() {
		this.modelo = modelos.getRowData();

		String retorno = "erro";

		try {
			ModeloDAO dao = new ModeloDAO();
			
			dao.Delete(modelo);
			
			String nome = modelo.getNome();

			// Mensagem de confirmação após deletar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modelo: "+nome+", excluído do Sistema"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updateModelo() {

		String retorno = "erro";

		try {
			ModeloDAO dao = new ModeloDAO();				
			
			dao.Update(modelo);
			
			String nome = modelo.getNome();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modelo: "+nome+" atualizada com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novoModelo() {
		modelo = new Modelo();
	}

	public String addModelo() {

		String retorno = "erro";

		try {
			ModeloDAO dao = new ModeloDAO();
			
			dao.Salvar(modelo);		
			
			String nome = modelo.getNome();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modelo: "+nome+", cadastrado com sucesso"));

			retorno = "modelo";
		} catch (Exception ex) {

		}
		return retorno;

	}	
	
	public Collection<SelectItem> getCarregarMarcas() {
		MarcaDAO dao = new MarcaDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione a marca --"));
		List<Marca> listaMarca = dao.GetALL();

		for (int i = 0; i < listaMarca.size(); i++) {
			lista.add(new SelectItem(listaMarca.get(i).getId(), listaMarca.get(i).getNome()));
		}

		return lista;
	}


}
