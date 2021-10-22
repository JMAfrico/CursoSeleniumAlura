package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.PageLeiloes;

public class PageLogin {

	
	private WebDriver browser;
	private final String URL_LOGIN = "http://localhost:8080/login";
	private final String URL_LEILOES = "http://localhost:8080/leiloes/2";

	
	public PageLogin() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		this.browser.navigate().to(URL_LOGIN);
		
	}
	
	public void fecharNavegador() {
		this.browser.quit();
	}
	
	public void preecheFormularioDeLogin(String username, String password) {
		this.browser.findElement(By.id("username")).sendKeys(username);
		this.browser.findElement(By.id("password")).sendKeys(password);
	}
	
	public PageLeiloes efetuaLogin() {
		this.browser.findElement(By.id("login-form")).submit();
		return new PageLeiloes(browser);
	}
	
	public boolean isPaginadeLogin() {
		return this.browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public String getNomeUsuarioLogado() {
		try {
			return this.browser.findElement(By.id("usuario-logado")).getText();
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaParaPaginaDeLances() {
		this.browser.navigate().to(URL_LEILOES);
		
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}

	public boolean isPaginadeLoginComDadosInvalidos() {
		return this.browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}
}
