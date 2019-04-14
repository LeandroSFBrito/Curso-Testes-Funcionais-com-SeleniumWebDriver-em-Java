package testesSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;



public class TesteAlert {
	
	// Interações com JavaScript
	// navegador.switchTo().alert().accept(); // ACEITAR A MENSAGEM DO ALERT DE JAVA SCRIPT
	
	private WebDriver navegador;
	private DSL dsl;
	
	// ############ METODO @Before e METODO @After
	
	@Before
	public void inicializa() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		navegador = new ChromeDriver();
		//navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.manage().window().maximize();
		navegador.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(navegador);
	}
	
	@After
	public void fechaNavegador() {
		
		navegador.quit();
	}
	
	
	
	// ############  METODOS DE TESTE #############
	
	
	@Test
	public void interagirComAlertSimples() {
		
	
		navegador.findElement(By.id("alert")).click(); 
		Alert alert = navegador.switchTo().alert(); // variavel "alert", interação com o JavaScript
		String texto = alert.getText(); // variavel "texto", para pegar o texto do alert JavaScript
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		
		navegador.findElement(By.xpath("//*[@id=\"elementosForm:nome\"]")).sendKeys(texto);
		
	}	
	
	@Test
	public void interagirConfirmSimples() {
		
		navegador.findElement(By.xpath("//*[@id=\"confirm\"]")).click();
		Alert alert = navegador.switchTo().alert();
		alert.accept();
		String texto = alert.getText();
		Assert.assertEquals("Confirmado", texto);	
		
	}
	
	@Test
	public void interagirConfirmSimplesNegado() {
				
		navegador.findElement(By.xpath("//*[@id=\"confirm\"]")).click();
		Alert alert = navegador.switchTo().alert();
		alert.dismiss();
		String texto = alert.getText();
		Assert.assertEquals("Negado", texto);
		  
	}
	
	@Test
	public void interagirComPrompt() {
			
		navegador.findElement(By.xpath("//*[@id=\"prompt\"]")).click();
		Alert alerta = navegador.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		
		
	}
	
	@Test // TERMINAR DE EDITAR
	public void cadastroComSucesso() {
		
		// AÇOES PARA PREENCHER O CADASTRO
		dsl.escreve("//*[@id=\"elementosForm:nome\"]", "Leandro" );
		dsl.escreve("//*[@id=\"elementosForm:sobrenome\"]", "Brito");
		dsl.clicarRadio("//*[@id=\"elementosForm:sexo:0\"]");
		dsl.clicarRadio("//*[@id=\"elementosForm:comidaFavorita:2\"]");
		WebElement formacao = navegador.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new 	Select(formacao);
		combo.selectByVisibleText("Mestrado");
		
		WebElement esporte = navegador.findElement(By.xpath("//*[@id=\"elementosForm:esportes\"]"));
		Select comboesp = new 	Select(esporte);
		comboesp.selectByVisibleText("Futebol");
		comboesp.selectByVisibleText("Corrida");
		
	    //dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
		//dsl.selecionarCombo("//*[@id=\"elementosForm:esportes\"]", "Futebol");
		//dsl.selecionarCombo("//*[@id=\"elementosForm:esportes\"]", "Corrida");
		dsl.escreve("//*[@id=\"elementosForm:sugestoes\"]", "Estudar");
		dsl.clicarBotao("//*[@id=\"elementosForm:cadastrar\"]");
		
		// VALIDAÇÃO DO CADASTRO
		Assert.assertEquals("Cadastrado!", navegador.findElement(By.xpath("//*[@id=\"resultado\"]/span")).getText());
		Assert.assertEquals("Nome: Leandro", navegador.findElement(By.xpath("//*[@id=\"descNome\"]")).getText());
		Assert.assertEquals("Sobrenome: Brito", navegador.findElement(By.xpath("//*[@id=\"descSobrenome\"]")).getText());
		Assert.assertEquals("Sexo: Masculino", navegador.findElement(By.xpath("//*[@id=\"descSexo\"]")).getText());
		Assert.assertEquals("Comida: Pizza", navegador.findElement(By.xpath("//*[@id=\"descComida\"]")).getText());
		Assert.assertEquals("Escolaridade: mestrado", navegador.findElement(By.xpath("//*[@id=\"descEscolaridade\"]")).getText());
		Assert.assertEquals("Esportes: Futebol Corrida", navegador.findElement(By.xpath("//*[@id=\"descEsportes\"]")).getText());
		Assert.assertEquals("Sugestoes: Estudar", navegador.findElement(By.xpath("//*[@id=\"descSugestoes\"]")).getText());
		
	}
}	
