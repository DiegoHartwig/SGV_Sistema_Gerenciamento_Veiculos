package br.net.hartwig.bean;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**  
 * S.G.V 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "janelaBean")
@SessionScoped
public class JanelasBean {

	// M�todos para abrir janelas dialog

	// registrar manuten��o
	public void abrirNovaManutencao() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 300);
		opcoes.put("contentWidth", 500);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("nova-manutencao", opcoes, null);
	}	
	
	// Cadastrar novo ve�culo
	public void abrirNovoVeiculo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 400);
		opcoes.put("contentWidth", 550);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("novo-veiculo", opcoes, null);
	}	
	
	
	// Cadastrar modelo de ve�culo
	public void abrirNovoModelo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 200);
		opcoes.put("contentWidth", 500);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("novo-modelo", opcoes, null);
	}	
	
	// Cadastrar marca de ve�culo
	public void abrirNovaMarca() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 120);
		opcoes.put("contentWidth", 380);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("nova-marca", opcoes, null);
	}
	
	// Visualizar informa��es do ve�culo
	public void abrirVisualizarVeiculo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 400);
		opcoes.put("contentWidth", 550);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("visualizar-veiculo", opcoes, null);
	}	
	
	// Cadastrar componente do ve�culo ex: pneu, transmiss�o
	public void abrirNovoComponente() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 200);
		opcoes.put("contentWidth", 500);
		opcoes.put("closeOnEscape", true);
		RequestContext.getCurrentInstance().openDialog("novo-componente", opcoes, null);
	}



}
