package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageLeiloes {

	
	private WebDriver browser;
	private final String URL_NEW_LEILAO = "http://localhost:8080/leiloes/new";

	
	public PageLeiloes(WebDriver browser) {
		this.browser = browser;
		
	}
	
	public void fecharNavegador() {
		this.browser.quit();
	}

	
	public void efetuaLogin() {
		this.browser.findElement(By.id("login-form")).submit();
	}

	public PageCadastroLeilao carregarFormulario() {
		browser.navigate().to(URL_NEW_LEILAO);
		return new PageCadastroLeilao(browser);
	}

	//verifica na pagina de leiloes, se o leilão foi cadastrado
	public boolean isLeilaoCadastrado(String nome, String valor, String data) {
		//recupera a ultima linha da tabela ultilizando CSS selector
		WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));

		//recuperacao de cada texto da coluna da linha acima
		WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaData = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValor = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

		//verifica se o texto da coluna recuperada é igual ao que foi passada por parâmetro
		return colunaNome.getText().equals(nome)
				&& colunaData.getText().equals(data)
				&& colunaValor.getText().equals(valor);
	}
	

}
