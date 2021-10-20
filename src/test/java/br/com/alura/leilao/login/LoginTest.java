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

	private WebDriver browser;
	private String URL_LOGIN = "http://localhost:8080/login";

	// Executa esse método antes de todos, uma única vez
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	}

	// Executa esse método antes de qualquer inicio de teste
	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}

	// Executa esse método depois de qualquer teste
	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {	
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();
		// ou browser.findElement(By.id("btn_login")).click();

		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));

		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

		// browser.quit();

		/*
		 * O que eu fiz foi abrir a conexao com a localizacao do driver do chrome
		 * Navegar até a página de login Procurar elemento txt pelo Id "username" na
		 * página HTML e enviar o texto fulano Procurar elemento txt pelo Id "password"
		 * na página HTML e enviar o texto pass Procurar elemento formulário onde estão
		 * os campos pelo Id "login-form" na página HTML e dar um submit, ou seja o
		 * mesmo que aperta o ok
		 * 
		 * Assert.assertFalse(browser.getCurrentUrl().equals(
		 * "http://localhost:8080/login")); Assert verifique se eu não estou mais na
		 * tela de login
		 * 
		 * Assert.assertEquals("fulano",
		 * browser.findElement(By.id("usuario-logado")).getText()); Assert verifique se
		 * o texto "fulano" é igual da id "usuario-logado"
		 */
	}

	@Test
	public void naoDeveriaLogarComDadosInválidos() {

//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//		WebDriver browser = new ChromeDriver();
		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123123");

		browser.findElement(By.id("login-form")).submit();
		// ou browser.findElement(By.id("btn_login")).click();

		// caso o login ou senha seja inválidos, continuar(assertTrue)na página, então
		// OK no teste
		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));

		// faz uma verificacao se na pagina toda(código fonte = getPageSource) contem a
		// String 'Usuário e senha inválidos.' então OK no teste
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));

		// faz uma verificacao se o exceção NoSuchElementException foi lançado, se foi
		// lançado, ou seja, não encontrou o label usuario-logado
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));

	}
}
