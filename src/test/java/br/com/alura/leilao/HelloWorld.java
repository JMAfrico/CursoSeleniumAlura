package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorld {

	@Test
	public void hello() {
		
		//Localização do webDriver
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		//instância de navegador
		WebDriver browser = new ChromeDriver();
		
		//abre o navegador e acessa o link
		browser.navigate().to("localhost:8080/leiloes");
		
		//fecha a janela do navegador
		browser.quit();
	}
}
