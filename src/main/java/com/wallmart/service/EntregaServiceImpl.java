package com.wallmart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wallmart.constants.Constants;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.model.entrega.Rota;
import com.wallmart.model.vo.AgrupadorDeRotasVO;
import com.wallmart.model.vo.EntregaVO;

@Service
public class EntregaServiceImpl {
	
	public EntregaVO calcularRota(Mapa mapa, String origem, String destino, Integer autonomia,Double valorGasolina) throws EntregaMercadoriaException {
		validar(mapa);		
		Map<String, List<Rota>> mapRotasPorOrigem = converterParaMap(mapa.getRotas());
		AgrupadorDeRotasVO processamentoEntrega = processarRotas(origem,destino, mapRotasPorOrigem, new AgrupadorDeRotasVO(Integer.MAX_VALUE));
		double custo = calcularCusto(processamentoEntrega.getTotalPercorrido(),autonomia,valorGasolina);
		return new EntregaVO(custo,processamentoEntrega.getLugaresPercorridos());
	}
	
	private void validar(Mapa mapa) throws EntregaMercadoriaException {
		if(mapa == null){
			throw new EntregaMercadoriaException(Constants.ITEM_NAO_ENCONTRADO);
		}
	}

	private AgrupadorDeRotasVO processarRotas(String origem,String destino,Map<String, List<Rota>> mapRotasPorOrigem, AgrupadorDeRotasVO agrupadorEntregas) throws EntregaMercadoriaException
	{
		List<Rota> rotasEncotradas = mapRotasPorOrigem.get(origem);
		AgrupadorDeRotasVO agrupadorLugaresFaltantes = new AgrupadorDeRotasVO(Integer.MAX_VALUE);
		if(rotasEncotradas == null || rotasEncotradas.isEmpty()){
			throw new EntregaMercadoriaException(Constants.ERRO_ROTA);
		}
		
		for (Rota rota : rotasEncotradas) {
			if(destino.equals(rota.getDestino())){
				agrupadorLugaresFaltantes = criarProcessamentoTemporario(rota);
				agrupadorLugaresFaltantes.adicionarLugar(rota.getDestino());
				break;
			}
			AgrupadorDeRotasVO agrupamentoProcessado = processarRotas(rota.getDestino(),destino,mapRotasPorOrigem,agrupadorLugaresFaltantes);
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

	private AgrupadorDeRotasVO criarProcessamentoTemporario(Rota rota) {
		AgrupadorDeRotasVO processamento = new AgrupadorDeRotasVO();
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

}
