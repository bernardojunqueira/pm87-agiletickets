package br.com.caelum.agiletickets.acceptance;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ReservaTest {
	
	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private SessoesPage sessoes;
	
	@BeforeClass
	public static void abreBrowser() {
		browser = new FirefoxDriver();
	}
	
	@Before
	public void setUp() throws Exception {
		sessoes = new SessoesPage(browser);
	}

	@AfterClass
	public static void teardown() {
		browser.close();
	}
	
	//cria estabelecimento
	//cria espetaculo
	//cria sessao
	//reserva
	
	@Test
	public void reservaUmIngressoDeUmaSessaoComPoucosIngressosDisponiveis() throws Exception {
		sessoes.abreListagem();
		sessoes.selecionaSessao();
		sessoes.reservaIngresso(1);
		sessoes.precoDoIngressoDeveSer(55);
		
	}
	
	@Test
	public void reservaUmIngressoDeUmaSessaoComPoucosIngressosDisponiveis() throws Exception {
		sessoes.abreListagem();
		sessoes.selecionaSessao();
		sessoes.reservaIngresso(1);
		sessoes.precoDoIngressoDeveSer(55);
		
	}
}
