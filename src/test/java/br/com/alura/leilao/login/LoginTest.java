package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private PageLogin pageLogin;

	@BeforeEach
	public void beforeEach() {
		this.pageLogin = new PageLogin();
	}

	@AfterEach
	public void afterEach() {
		pageLogin.fecharNavegador();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {	
		pageLogin.preecheFormularioDeLogin("fulano", "pass");
		pageLogin.efetuaLogin();

		Assert.assertFalse(pageLogin.isPaginadeLogin());
		Assert.assertEquals("fulano", pageLogin.getNomeUsuarioLogado());

	}

	@Test
	public void naoDeveriaLogarComDadosInválidos() {

		pageLogin.preecheFormularioDeLogin("invalido", "123123");
		pageLogin.efetuaLogin();

		Assert.assertTrue(pageLogin.isPaginadeLoginComDadosInvalidos());
		Assert.assertNull(pageLogin.getNomeUsuarioLogado());
		Assert.assertTrue(pageLogin.contemTexto("Usuário e senha inválidos."));

	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		pageLogin.navegaParaPaginaDeLances();
		Assert.assertTrue(pageLogin.isPaginadeLogin());
		Assert.assertFalse(pageLogin.contemTexto("Dados do leilão"));
		
	}
}
