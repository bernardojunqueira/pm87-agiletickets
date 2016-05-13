package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void NaoDeveCriarSessaoDiariaSeDataInicioForMaiorQueDataFim() {
		Espetaculo acdc = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		
		List<Sessao> sessoes = acdc.criaSessoes(dia, dia.minusDays(1), hora, Periodicidade.DIARIA);
		
		Assert.assertEquals(0, sessoes.size());
	}
	
	@Test
	public void NaoDeveCriarSessaoSemanalSeDataInicioForMaiorQueDataFim() {
		Espetaculo acdc = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		
		List<Sessao> sessoes = acdc.criaSessoes(dia, dia.minusWeeks(1), hora, Periodicidade.SEMANAL);
		
		Assert.assertEquals(0, sessoes.size());
	}
	
	@Test
	public void DeveCriarApenasUmaSessaoDiariaQuandoAsDatasForemIguais() {
		Espetaculo acdc = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		
		List<Sessao> sessoes = acdc.criaSessoes(dia, dia, hora, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(acdc, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
		
	}
	
	@Test
	public void DeveCriarApenasUmaSessaoSemanalQuandoAsDatasForemIguais() {
		Espetaculo acdc = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		
		List<Sessao> sessoes = acdc.criaSessoes(dia, dia, hora, Periodicidade.SEMANAL);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(acdc, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
		
	}
	
	@Test
	public void DeveCriarUmaSessaoDiariaParaCadaDiaEntreAsDatas() {
		Espetaculo acdc = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		
		List<Sessao> sessoes = acdc.criaSessoes(dia, dia.plusDays(5), hora, Periodicidade.DIARIA);
		
		Assert.assertEquals(6, sessoes.size());
		
		for (Integer i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(acdc, sessoes.get(i).getEspetaculo());
			Assert.assertEquals(dia.plusDays(i).toDateTime(hora), sessoes.get(i).getInicio());
		}
	}
	
	@Test
	public void DeveCriarUmaSessaoSemanalParaCadaSemanaEntreAsDatas() {
		Espetaculo acdc = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		
		List<Sessao> sessoes = acdc.criaSessoes(dia, dia.plusWeeks(5), hora, Periodicidade.SEMANAL);
		
		Assert.assertEquals(6, sessoes.size());
		
		for (Integer i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(acdc, sessoes.get(i).getEspetaculo());
			Assert.assertEquals(dia.plusWeeks(i).toDateTime(hora), sessoes.get(i).getInicio());
		}
	}
	
	@Test
	public void deveriaCriarApenasUmaSessaoSeDatasDeInicioEFimForemIguaisEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, hoje, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
		Assert.assertEquals(impeachment, criadas.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), criadas.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriarApenasUmaSessaoSeDatasDeInicioEFimForemIguaisEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, hoje, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
		Assert.assertEquals(impeachment, criadas.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), criadas.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriar11SessoesSeIntervaloDeDatasForDe10DiasEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalDate daqui10Dias = hoje.plusDays(10);
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, daqui10Dias, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(11, criadas.size());
		for (int i = 0; i < 11; i++) {
			Assert.assertEquals(impeachment, criadas.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusDays(i).toDateTime(agora), criadas.get(i).getInicio());
		}
	}
	
	@Test
	public void deveriaCriar4SessoesSeIntervaloDeDatasForDe3SemanasEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalDate daqui3Semanas= hoje.plusWeeks(3);
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, daqui3Semanas, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(4, criadas.size());
		for (int i = 0; i < 4; i++) {
			Assert.assertEquals(impeachment, criadas.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusWeeks(i).toDateTime(agora), criadas.get(i).getInicio());
		}
	}
	
	@Test
	public void naoDeveriaCriarSessoesSeDataInicioForMaiorQueDataFimEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate amanha = new LocalDate().plusDays(1);
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(amanha, hoje, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
	}
	
	@Test
	public void naoDeveriaCriarSessoesSeDataInicioForMaiorQueDataFimEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate amanha = new LocalDate().plusDays(1);
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(amanha, hoje, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(0, criadas.size());
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
