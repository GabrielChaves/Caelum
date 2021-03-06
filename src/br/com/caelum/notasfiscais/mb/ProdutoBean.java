package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@RequestScoped
public class ProdutoBean {
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	@Inject
	ProdutoDao dao;
	
	public Produto getProduto() {
		return produto;
	}	
	
	public List<Produto> getProdutos(){
		if(produtos == null){
			System.out.println("carregando produtos");
			produtos = dao.listaTodos();
		}
		return produtos;
	}
	
	@Transactional
	public void grava(){
		
		if(produto.getId() == null){
			dao.adiciona(produto);
		}else{
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		produtos = dao.listaTodos();
	}
	
	@Transactional
	public void remove(Produto produto){
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@Transactional
	public void cancelar(){
		System.out.println("apagando");
		this.produto = new Produto();
	}
	
	
	
	
	
	
}
