package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCadastroLeilao {

	private final String URL_NEW_LEILAO = "http://localhost:8080/leiloes";

	private WebDriver browser;
	

	public PageCadastroLeilao(WebDriver browser) {
		this.browser = browser;
	}
	
	public void fecharNavegador() {
		this.browser.quit();
	}

	public PageLeiloes cadastrarLeilao(String nome,String valorInicial,String data) {
		this.browser.findElement(By.id("nome")).sendKeys(nome);
		this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
		this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
		this.browser.findElement(By.id("button-submit")).click();
		
		return new PageLeiloes(browser);
		
		
		
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().equals(URL_NEW_LEILAO);
	}

	public boolean isMensagensValidacao() {
		String pageSource = browser.getPageSource();
		return pageSource.contains("minimo 3 caracteres")
				&& pageSource.contains("não deve estar em branco")
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
		
	}
	
	
}
