package com.wallmart.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.wallmart.model.json.MapaJSON;
import com.wallmart.model.json.RotaJSON;
import com.wallmart.model.mapa.Mapa;
import com.wallmart.model.mapa.Rota;

@Component
public class MapaJSONConverter {
	
	String[] ignoresRota = {"autonomia","valorCombustivel","id"};
	String[] ignores = {"rotas"};

	public List<MapaJSON> converToListJSON(List<Mapa> mapas) {
		List<MapaJSON> mapasJSON = new ArrayList<MapaJSON>();
		if(mapas != null){
			for (Mapa mapa : mapas) {
				MapaJSON mapaJSON = convertToJSON(mapa);
				if(mapaJSON != null){
					mapasJSON.add(mapaJSON);
				}
			}
		}
		return mapasJSON;
	}

	public MapaJSON convertToJSON(Mapa mapa) {
		if(mapa == null){
			return null;
		}
		MapaJSON mapaJSON = new MapaJSON();
		BeanUtils.copyProperties(mapa, mapaJSON, ignores);
		List<RotaJSON> rotasJSON = convertToJSON(mapa.getRotas());
		mapaJSON.setRotas(rotasJSON);
		return mapaJSON;
	}

	private List<RotaJSON> convertToJSON(List<Rota> rotas) {
		List<RotaJSON> rotasJSON = new ArrayList<RotaJSON>();
		if(rotas != null){
			for (Rota rota : rotas) {
				if(rota != null){
					RotaJSON rotaJSON = new RotaJSON();
					BeanUtils.copyProperties(rota, rotaJSON,ignoresRota);
					rotasJSON.add(rotaJSON);
				}
			}
		}
		return rotasJSON;
	}

	public Mapa convertToModel(MapaJSON mapaJSON) {
		if(mapaJSON == null){
			return null;
		}
		Mapa mapa = new Mapa();
		BeanUtils.copyProperties(mapaJSON,mapa, ignores);
		List<Rota> rotas = convertToModel(mapaJSON.getRotas());
		mapa.setRotas(rotas);
		return mapa;
	}

	private List<Rota> convertToModel(List<RotaJSON> rotasJSON) {
		List<Rota> rotas = new ArrayList<Rota>();
		if(rotasJSON != null){
			for (RotaJSON rotaJSON : rotasJSON) {
				if(rotaJSON != null){
					Rota rota = new Rota();
					BeanUtils.copyProperties(rotaJSON,rota,ignoresRota);
					rotas.add(rota);
				}
			}	
		}
		return rotas;
	}
}
