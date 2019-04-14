package testesSelenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

public class TesteGoogle {
	
	@Test
	public void Teste() {
		// CONEXÃO COM O CHROME DRIVER
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		
		// OBJETO NAVEGADOR, QUE RECEBE O VALOR DO CHROME DRIVER
		WebDriver navegador = new ChromeDriver();
		
		// ENDEREÇO DO TESTE NO OBJETO NAVEGADOR
		navegador.get("https://www.google.com");
		
		// UTILIZADO PARA MAXIMIZAR O NAVEGADOR ASSIM QUE INICIAR O TESTE
		//navegador.manage().window().maximize();
		
		// COMPARAÇÃO DO TITULO DO NAVEGADOR
	    //Assert.assertEquals("Google", navegador.getTitle());
	    
	    
	    
	}

}
