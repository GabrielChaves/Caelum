package br.com.caelum.notasfiscais.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception{
		System.out.print("Abrindo a transação");
		manager.getTransaction().begin();
		
		//Passando para o JSF tratar a requisição e pegando o retorno da logica
		Object resultado = ctx.proceed();
		
		manager.getTransaction().commit();
		System.out.println("commitando a transação");
		
		return resultado;
	}
	
	
}
