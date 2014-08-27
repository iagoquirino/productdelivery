package com.wallmart.service.interfaces;

import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.model.vo.EntregaVO;

public interface IEntregaService {

	EntregaVO calcularRota(Mapa mapa, String origem, String destino,Integer autonomia, Double valorCombustivel) throws EntregaMercadoriaException;

}
