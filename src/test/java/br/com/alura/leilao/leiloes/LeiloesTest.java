package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.PageLogin;

public class LeiloesTest {

	private PageLeiloes pageLeiloes;
	private PageCadastroLeilao pageCadastroLeiloes;

	@BeforeEach
	public void beforeEach() {
		//PARA CADASTRAR O PRODUTO NO LEILAO, PRIMEIRO SE DEVE ESTAR LOGADO
		PageLogin pageLogin = new PageLogin();
		
		//PREENCHER O FORMULÁRIO DE CADASTRO
		pageLogin.preecheFormularioDeLogin("fulano","pass");
		
		//FAÇO UMA CÓPIA DE REFERÊNCIA, PASSANDO O PAGELOGIN PARA O PAGELEILOES
		this.pageLeiloes = pageLogin.efetuaLogin();
		
		//FAÇO UMA CÓPIA DE REFERÊNCIA, PASSANDO O PAGELEILOES PARA PAGEDECADASTRO,
		//ABRINDO O FORMULÁRIO DE CADASTRO
		this.pageCadastroLeiloes = pageLeiloes.carregarFormulario();
	}

	@AfterEach
	public void afterEach() {
		pageLeiloes.fecharNavegador();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {

		//Criação de variáveis para armazenar os campos
		String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilão do dia "+data;
		String valor = "500.00";
		
		//Método para preencher os campos
		this.pageLeiloes = pageCadastroLeiloes.cadastrarLeilao(nome,valor,data);
		
		Assert.assertTrue(pageLeiloes.isLeilaoCadastrado(nome,valor,data));

	}
	
	@Test
	public void naoDeveriaCadastrarLeilaoComDadosInvalidos() {
		this.pageLeiloes = pageCadastroLeiloes.cadastrarLeilao("","","");
		Assert.assertTrue(this.pageCadastroLeiloes.isPaginaAtual());
		Assert.assertTrue(this.pageCadastroLeiloes.isMensagensValidacao());

	}
	
	

}
