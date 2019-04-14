package testesSelenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver navegador; 
	
	public DSL(WebDriver navegador) {
		this.navegador = navegador;
	
    }
	
	public void escreve(String xpath_campo, String texto) {
		
		navegador.findElement(By.xpath(xpath_campo)).sendKeys(texto);
	}
	
	public String obterValorCampo(String xpath_campo) {
		return navegador.findElement(By.xpath(xpath_campo)).getAttribute("value");
		
	}
	
	public void clicarRadio(String xpath) {
	     navegador.findElement(By.xpath(xpath)).click();
		
	}
	
	public void clicarBotao(String xpath) {
	     navegador.findElement(By.xpath(xpath)).click();
		
	}

	public boolean validarRadioMarcado(String xpath) {
		return navegador.findElement(By.xpath(xpath)).isSelected();		
	}
	
	public void selecionarCombo(String xpath_combo, String valor) {
		WebElement element = navegador.findElement(By.xpath(xpath_combo));
		Select combo = new 	Select(element);
		combo.selectByVisibleText(valor);	
	}
	
	public String obterValorCombo(String xpath) {
		WebElement element = navegador.findElement(By.xpath(xpath));
		Select combo = new 	Select(element);	
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarLink(String link) {
		navegador.findElement(By.linkText(link)).click(); 
	}
	
	public String obterTexto(String xpath) {
		return navegador.findElement(By.xpath(xpath)).getText();
	}
	
	

}
