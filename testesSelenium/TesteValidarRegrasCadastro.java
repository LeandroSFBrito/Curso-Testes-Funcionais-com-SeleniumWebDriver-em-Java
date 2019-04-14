package testesSelenium;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


public class TesteValidarRegrasCadastro {
	
	
	public WebDriver navegador;
	
	// ############ METODO @Before e METODO @After
	
	@Before
	public void inicializa() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
	    navegador = new ChromeDriver();
		//navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.manage().window().maximize();
		navegador.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
	}
	
	@After
	public void fechaNavegador() {
		
		navegador.quit();
	}
	
		
	
	// ############  METODOS DE TESTE #############
	

	
	@Test	
	public void validarNomeObrigatorio() {
	
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:cadastrar\"]")).click();
		Alert alert = navegador.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Nome eh obrigatorio", texto);
			
	}
	
	@Test
	public void validarSobrenomeObrigatorio() {
	
		
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:nome\"]")).sendKeys("Leandro");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:cadastrar\"]")).click();
		
		Alert alert = navegador.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Sobrenome eh obrigatorio", texto);
		
	}
	
	@Test
	public void validarSexoObrigatorio() {
		
		
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:nome\"]")).sendKeys("Leandro");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:sobrenome\"]")).sendKeys("Brito");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:cadastrar\"]")).click();
		
		Alert alert = navegador.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Sexo eh obrigatorio", texto);
		
		
	}
	
	@Test
	public void validarOpcaoDeComida() {
				
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:nome\"]")).sendKeys("Leandro");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:sobrenome\"]")).sendKeys("Brito");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:sexo:0\"]")).click();
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:comidaFavorita:0\"]")).click();
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:comidaFavorita:3\"]")).click();
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:cadastrar\"]")).click();
		
		Alert alert = navegador.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", texto);	
		
	}
	
	@Test
	public void validarSelecaoEsportes() {
		
		
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:nome\"]")).sendKeys("Leandro");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:sobrenome\"]")).sendKeys("Brito");
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:sexo:0\"]")).click();
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:comidaFavorita:2\"]")).click();
		WebElement elementEsporte = navegador.findElement(By.xpath("//*[@id=\"elementosForm:esportes\"]"));
	    Select comboEsporte = new Select(elementEsporte);
	    comboEsporte.selectByVisibleText("Futebol");
	    comboEsporte.selectByVisibleText("O que eh esporte?" );		
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:cadastrar\"]")).click();
		
		Alert alert = navegador.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Voce faz esporte ou nao?", texto);
		alert.accept();                         
		
	}
}
