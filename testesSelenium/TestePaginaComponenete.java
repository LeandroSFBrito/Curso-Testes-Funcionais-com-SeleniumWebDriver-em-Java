package testesSelenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class TestePaginaComponenete {
	
	private WebDriver navegador;
	private DSL dsl;
	
	// ############ METODO @Before e METODO @After
	
	@Before
	public void inicializa() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().window().maximize();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		navegador.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");	
		dsl = new DSL(navegador);
		
	}
	
	@After
	public void fechaNavegador() {
		
		navegador.quit();
	}
	
	
	
	// ############  METODOS DE TESTE #############
	
	
	
	
	@Test
	public void testeTextField() {
		dsl.escreve("//*[@id=\"elementosForm:nome\"]", "Teste de escrita");		
	    Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("//*[@id=\"elementosForm:nome\"]"));
	}
	
	@Test
	public void testeTextArea() {
		dsl.escreve("//*[@id=\"elementosForm:sugestoes\"]","teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("//*[@id=\"elementosForm:sugestoes\"]"));		
	}
	
	@Test
	public void campoRadioButton() {
		
		dsl.clicarRadio("//*[@id=\"elementosForm:sexo:0\"]");
		Assert.assertTrue(navegador.findElement(By.xpath("//*[@id=\"elementosForm:sexo:0\"]")).isSelected());

	}
	
	@Test
	public void campoCheckBox() {
		
		dsl.clicarRadio("//*[@id=\"elementosForm:comidaFavorita:2\"]");
		Assert.assertTrue(dsl.validarRadioMarcado("//*[@id=\"elementosForm:comidaFavorita:2\"]"));

	}
	
	@Test
	public void campoComboBox() {
		dsl.selecionarCombo("//*[@id=\"elementosForm:escolaridade\"]", "2o grau completo");	
		assertEquals("2o grau completo", dsl.obterValorCombo("//*[@id=\"elementosForm:escolaridade\"]"));
		}
	
	/*
	@Test
	public void verificarValoresCombo() {
		
		WebElement element = navegador.findElement(By.xpath("//*[@id=\"elementosForm:escolaridade\"]"));
		Select combo = new 	Select(element);
		//List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		
		
	}	
	*/
		
	@Test	
	public void comboMultiplaEscolhas() {
	
		WebElement element = navegador.findElement(By.xpath("//*[@id=\"elementosForm:esportes\"]"));
		Select combo = new 	Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		java.util.List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions);
	
	}
	
	@Test
	public void interacaoComBotao() {
		
		// Validar o nome do botão após clicar nele
		
		dsl.clicarRadio("//*[@id=\"buttonSimple\"]");
		WebElement botao = navegador.findElement(By.xpath("//*[@id=\"buttonSimple\"]"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	

	@Test
	public void deveBuscarLinksNaTela() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("//*[@id=\"resultado\"]"));
	}
	
	
	

}
