package com.wallmart.service.interfaces;

import java.util.List;

import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;

public interface IMapService {

	Map find(Long id) throws ProductDeliveryException;
	
	Map find(String name);

	List<Map> list();

	Long save(Map map);

	void delete(Map map);

}
