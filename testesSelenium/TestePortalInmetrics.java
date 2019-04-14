package testesSelenium;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestePortalInmetrics {
	
	private WebDriver navegador;
	
	@Test
	public void testePortalDoColaborador() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().window().maximize();
		//navegador.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		navegador.get("https://aplic.inmetrics.com.br/");
		navegador.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/a/img")).click();
		WebDriverWait wait = new WebDriverWait(navegador, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtUsuario")));
		navegador.findElement(By.id("txtUsuario")).sendKeys("leandro.brito");
		navegador.findElement(By.xpath("//*[@id=\"txtSenha\"]")).sendKeys("Ls@inm2018");
		navegador.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();
			
	}

}
