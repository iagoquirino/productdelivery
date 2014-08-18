package com.wallmart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallmart.constants.Constants;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.AgrupadorDeRotas;
import com.wallmart.model.Entrega;
import com.wallmart.model.mapa.Mapa;
import com.wallmart.model.mapa.Rota;
import com.wallmart.service.validators.EntregaServiceValidator;

@Service
public class EntregaServiceImpl {
	
	@Autowired
	private EntregaServiceValidator entregaServiceValidator;

	public Entrega calcularRota(Mapa mapa, String origem, String destino, Integer autonomia,Double valorGasolina) throws EntregaMercadoriaException {
		entregaServiceValidator.validar(mapa,origem,destino,autonomia,valorGasolina);		
		Map<String, List<Rota>> mapRotasPorOrigem = converterParaMap(mapa.getRotas());
		AgrupadorDeRotas processamentoEntrega = processarRotas(origem,destino, mapRotasPorOrigem, new AgrupadorDeRotas(Integer.MAX_VALUE));
		double custo = calcularCusto(processamentoEntrega.getTotalPercorrido(),autonomia,valorGasolina);
		return new Entrega(custo,processamentoEntrega.getLugaresPercorridos());
	}

	private AgrupadorDeRotas processarRotas(String origem,String destino,Map<String, List<Rota>> mapRotasPorOrigem, AgrupadorDeRotas agrupadorEntregas) throws EntregaMercadoriaException
	{
		List<Rota> rotasEncotradas = mapRotasPorOrigem.get(origem);
		AgrupadorDeRotas agrupadorLugaresFaltantes = new AgrupadorDeRotas(Integer.MAX_VALUE);
		if(rotasEncotradas == null || rotasEncotradas.isEmpty()){
			throw new EntregaMercadoriaException(Constants.ERRO_ROTA);
		}
		
		for (Rota rota : rotasEncotradas) {
			if(destino.equals(rota.getDestino())){
				agrupadorLugaresFaltantes = criarProcessamentoTemporario(rota);
				agrupadorLugaresFaltantes.adicionarLugar(rota.getDestino());
				break;
			}
			AgrupadorDeRotas agrupamentoProcessado = processarRotas(rota.getDestino(),destino,mapRotasPorOrigem,agrupadorLugaresFaltantes);
			if(agrupamentoProcessado.getTotalPercorrido() < agrupadorEntregas.getTotalPercorrido())
			{
				agrupadorEntregas = criarProcessamentoTemporario(rota);
				agrupadorEntregas.adicionarDistancia(agrupamentoProcessado.getTotalPercorrido());
				agrupadorEntregas.adicionarLugares(agrupamentoProcessado.getLugaresPercorridos());
			}
		}
		
		if(agrupadorLugaresFaltantes.getTotalPercorrido() < agrupadorEntregas.getTotalPercorrido())
		{
			return agrupadorLugaresFaltantes;	
		}
		return agrupadorEntregas;
	}

	private AgrupadorDeRotas criarProcessamentoTemporario(Rota rota) {
		AgrupadorDeRotas processamento = new AgrupadorDeRotas();
		processamento.adicionarLugar(rota.getOrigem());
		processamento.adicionarDistancia(rota.getDistancia());
		return processamento;
	}

	private double calcularCusto(int totalDistancia, int autonomia, double valorGasolina) {
		return  valorGasolina * totalDistancia /autonomia;
	}
	
	private Map<String, List<Rota>> converterParaMap(List<Rota> rotas) {
		Map<String, List<Rota>> map = new HashMap<String, List<Rota>>();
		for (Rota rota : rotas) {
			String key = rota.getOrigem();
			if(!map.containsKey(key)){
				map.put(key, new ArrayList<Rota>());
			}
			map.get(key).add(rota);
		}
		return map;
	}

	public void setEntregaServiceValidator(
			EntregaServiceValidator entregaServiceValidator) {
		this.entregaServiceValidator = entregaServiceValidator;
	}
}
