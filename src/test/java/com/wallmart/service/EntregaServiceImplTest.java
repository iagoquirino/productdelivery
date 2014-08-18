package com.wallmart.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.wallmart.constants.Constants;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.model.vo.EntregaVO;

public class EntregaServiceImplTest {

	private EntregaServiceImpl entregaServiceImpl = new EntregaServiceImpl();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void deveRetornarMenorRotaEntre_A_B_Mapa() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa(),"A","B",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 2);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(2.5));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_A_D_Mapa() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa(),"A","D",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 3);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "D");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(6.25));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_A_E_Mapa() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa(),"A","E",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 4);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "D");
		Assert.assertEquals(entrega.getPontos().get(3).getNome(), "E");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(13.75));
	}
	
	@Test
	public void deveValidarQuandoNaoEncontradoUmaRotaDeDestinoInvalida(){
		try{
			entregaServiceImpl.calcularRota(getMapa(),"A","F",10,2.5);
			Assert.fail();	
		}catch(EntregaMercadoriaException e){
			Assert.assertEquals(Constants.ERRO_ROTA, e.getMensagem());
		}
	}
	
	@Test
	public void deveValidarQuandoNaoEncontradoUmaRotaDeOrigemInvalida(){
		try{
			entregaServiceImpl.calcularRota(getMapa(),"F","A",10,2.5);
			Assert.fail();	
		}catch(EntregaMercadoriaException e){
			Assert.assertEquals(Constants.ERRO_ROTA, e.getMensagem());
		}
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_B_E_Mapa() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa(),"B","E",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 3);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "D");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "E");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(11.25));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_A_B_Mapa2() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa2(),"A","B",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 2);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(2.5));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_A_D_Mapa2() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa2(),"A","D",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 3);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "D");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(6.25));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_A_E_Mapa2() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa2(),"A","E",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 4);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "D");
		Assert.assertEquals(entrega.getPontos().get(3).getNome(), "E");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(13.75));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_B_E_Mapa2() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa2(),"B","E",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 3);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "D");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "E");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(11.25));
	}
	
	@Test
	public void deveRetornarMenorRotaEntre_A_E_Mapa3() throws EntregaMercadoriaException{
		EntregaVO entrega = entregaServiceImpl.calcularRota(getMapa3(),"A","E",10,2.5);
		Assert.assertNotNull(entrega);
		Assert.assertEquals(entrega.getPontos().size(), 3);
		Assert.assertEquals(entrega.getPontos().get(0).getNome(), "A");
		Assert.assertEquals(entrega.getPontos().get(1).getNome(), "B");
		Assert.assertEquals(entrega.getPontos().get(2).getNome(), "E");
		Assert.assertEquals(Double.toString(entrega.getCusto()), Double.toString(3.75));
	}
	
	private Mapa getMapa(){
		Mapa mapa = new Mapa();
		mapa.adicionarRota("A","B",10);
		mapa.adicionarRota("B","D",15);
		mapa.adicionarRota("A","C",20);
		mapa.adicionarRota("C","D",30);
		mapa.adicionarRota("B","E",50);
		mapa.adicionarRota("D","E",30);
		return mapa;
	}
	
	private Mapa getMapa2(){
		Mapa mapa = new Mapa();
		mapa.adicionarRota("A","B",10);
		mapa.adicionarRota("B","D",15);
		mapa.adicionarRota("A","C",5);
		mapa.adicionarRota("C","D",30);
		mapa.adicionarRota("B","E",50);
		mapa.adicionarRota("D","E",30);
		return mapa;
	}
	
	private Mapa getMapa3(){
		Mapa mapa = new Mapa();
		mapa.adicionarRota("A","B",10);
		mapa.adicionarRota("B","D",15);
		mapa.adicionarRota("A","C",5);
		mapa.adicionarRota("C","D",30);
		mapa.adicionarRota("B","E",5);
		mapa.adicionarRota("D","E",30);
		return mapa;
	}
}
