package br.com.caelum.notasfiscais.mb;



import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.modelo.Usuario;


@Named
@RequestScoped
public class LoginBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	public String efetuaLogin(){
		boolean loginValido = dao.existe(this.usuario);
		
		if(loginValido){
			usuarioLogado.logar(usuario);
			return "produto?faces-redirect=true";
		}else {
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			return "login";
		}
		//System.out.println("O Login era valido " + loginValido);
	}
	
	public String logout(){
		System.out.println("saindo");
		this.usuario = new Usuario();
		usuarioLogado.deslogar();
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
