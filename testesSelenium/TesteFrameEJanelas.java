package testesSelenium;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.junit.Before;

public class TesteFrameEJanelas {
	
	private WebDriver navegador;
	
	// ############ METODO @Before e METODO @After
	
	@Before
	public void inicializa() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		navegador = new ChromeDriver();
		//navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.manage().window().maximize();
		navegador.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
	}
	
	public void fechaNavegador() {
		
		navegador.quit();
	}
	
	
	// ############  METODOS DE TESTE #############
	
	
	@Test
	public void interagirComFrame() {
		
		
		navegador.switchTo().frame("frame1"); // Alterar o foco para interagir com o Frame
		navegador.findElement(By.id("frameButton")).click(); // Após mudar o foco é possivel clicar no botão correto
		Alert alert = navegador.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Frame OK!", texto);
		alert.accept();
		
		navegador.switchTo().defaultContent(); // Retorna o foco para a pagina principal, saindo da area do Frame
		navegador.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	
	}
	
	@Test
	public void interagirComJanelas() {
		
		
		navegador.findElement(By.xpath("//*[@id=\"buttonPopUpEasy\"]")).click(); // clica no botão "Abrir PopUp"
		navegador.switchTo().window("Popup"); // Altera o foco para a janela principal para a janela de "PopUp"
		navegador.findElement(By.tagName("textarea")).sendKeys("Deu certo?"); // Escreve dentro da tag "textarea" dentro do PopUp
		navegador.close(); // Fecha o PopUp
		
		navegador.switchTo().window(""); // Retorna o foco para a janela principal
		navegador.findElement(By.tagName("textarea")).sendKeys("E agora?"); // Escreve dentro da tag "textarea" na tela principal
		
	}
	
	
	// Maneira Generica de como realizar o metodo acima com Janelas
	@Test
	public void interagirComJanelasSemTitulo() {
		

		navegador.findElement(By.xpath("//*[@id=\"buttonPopUpHard\"]")).click();
		System.out.println(navegador.getWindowHandle()); // Lita no console o numero do Window Handle da pagina atual
		System.out.println(navegador.getWindowHandles()); // Lista no console o numero do Window Handle das paginas abertas no momento
		navegador.switchTo().window((String) navegador.getWindowHandles().toArray()[1]); //Altera o foco para a pagina correspondente ao numero da listagem através do Array
		navegador.findElement(By.tagName("textarea")).sendKeys("Deu Certo?"); // Escreve na tag "Textarea" 
		navegador.switchTo().window((String) navegador.getWindowHandles().toArray()[0]); //Altera o foco para a pagina correspondente ao numero da listagem através do Array
		navegador.findElement(By.tagName("textarea")).sendKeys("E agora?"); // Escreve na tag "Textarea"
	}
	
	

}
