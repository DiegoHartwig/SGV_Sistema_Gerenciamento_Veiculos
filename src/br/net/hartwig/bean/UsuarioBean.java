
package br.net.hartwig.bean;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.UsuarioDAO;
import br.net.hartwig.filter.SessionUtil;
import br.net.hartwig.model.Usuario;

/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@SessionScoped
@ManagedBean(name = "usuarioBean")

public class UsuarioBean implements Serializable{	

	private static final long serialVersionUID = -4408286168366709607L;

	private Usuario usuario = new Usuario();
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private DataModel<Usuario> usuarios;
	
	//Variável que armazena o usuario selecionado no dataTable
	private Usuario usuarioSelecionado;
	
	//usuario da sessao
	private String UsuarioLog;		
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void setUsuarios(DataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String getUsuarioLog() {
		return UsuarioLog;
	}

	public void setUsuarioLog(String usuarioLog) {
		UsuarioLog = usuarioLog;
	}

	public void selecionarTecnico() {
		this.usuario = usuarios.getRowData();
	}

	public DataModel<Usuario> getUsuarios() {
		UsuarioDAO dao = new UsuarioDAO();

		try {
			List<Usuario> lista = dao.GetALL();
			usuarios = new ListDataModel<Usuario>(lista);
		} catch (Exception e) {

		}

		return usuarios;
	}

	// delete 
	public String deleteUsuario() {
		this.usuario = usuarios.getRowData();

		String retorno = "erro";

		try {
			UsuarioDAO dao = new UsuarioDAO();
			
			dao.Delete(usuario);
			
			String nome = usuario.getNome();

			// Mensagem de confirmação após deletar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário: "+nome+", excluído do Sistema"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updateUsuario() {

		String retorno = "erro";

		try {
			UsuarioDAO dao = new UsuarioDAO();				
			
			dao.Update(usuario);
			
			String nome = usuario.getNome();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados do usuário: "+nome+", atualizados com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novoUsuario() {
		usuario = new Usuario();
	}

	public String addUsuario() {

		String retorno = "erro";

		try {
			UsuarioDAO dao = new UsuarioDAO();

			String nome = usuario.getNome();
			dao.Salvar(usuario);			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário: "+nome+", cadastrado com sucesso"));

			retorno = "usuarios";
		} catch (Exception ex) {

		}
		return retorno;

	}


	//autenticação 
	public String logar(){
		
		usuario = usuarioDAO.getAutenticaUsuario(usuario.getLogin(), usuario.getSenha());
		
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "login ou senha incorreto!",
							"Erro no login"));
			return null;
		}else {
			
			//Se digitar um login e senha correto
			Object b = new Object();
			
			SessionUtil.setParam("UsuarioLogado", b);
			UsuarioLog = usuario.getLogin();
			System.out.println(UsuarioLog);
			return "/restrito/manutencao.xhtml?faces-redirect=true";
			
		}
	}
	
	//Método logout
			public String logout() { 
				FacesContext.getCurrentInstance().getExternalContext() .invalidateSession();
				
				return "/index?faces-redirect=true"; 
			
			
			}

}
