package com.wallmart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;




import com.wallmart.constants.Constants;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;
import com.wallmart.model.delivery.Route;
import com.wallmart.model.vo.RoutesGroupVO;
import com.wallmart.model.vo.DeliveryVO;
import com.wallmart.model.vo.PointVO;
import com.wallmart.service.interfaces.IDeliveryService;

@Service
public class DeliveryServiceImpl implements IDeliveryService {
	
	private static final double ZERO = 0.0;

	public DeliveryVO calculateRoutes(Map map, String origin, String destination, Integer autonomy,Double gasCost) throws ProductDeliveryException {
		validate(map);		
		HashMap<String, List<Route>> originRoutes = convertToHashMap(map.getRoutes());
		if(origin.equals(destination) && originRoutes.containsKey(origin)){
			return new DeliveryVO(ZERO,Arrays.asList(new PointVO(origin)));	
		}
		RoutesGroupVO deliveryProcess = processRoutes(origin,destination, originRoutes, new RoutesGroupVO(Integer.MAX_VALUE));
		double cost = calculateCost(deliveryProcess.getTotalPercorrido(),autonomy,gasCost);
		return new DeliveryVO(cost,deliveryProcess.getLugaresPercorridos());
	}
	
	private void validate(Map map) throws ProductDeliveryException {
		if(map == null){
			throw new ProductDeliveryException(Constants.ITEN_NOT_FOUND);
		}
	}

	private RoutesGroupVO processRoutes(String origin,String destination,HashMap<String, List<Route>> hashMapOriginRoutes, RoutesGroupVO groupRoutes) throws ProductDeliveryException
	{
		List<Route> foundRoutes = hashMapOriginRoutes.get(origin);
		RoutesGroupVO groupMissingPlaces = new RoutesGroupVO(Integer.MAX_VALUE);
		if(foundRoutes == null || foundRoutes.isEmpty()){
			throw new ProductDeliveryException(Constants.ERROR_ROUTE_NOT_FOUND);
		}
		
		for (Route route : foundRoutes) {
			if(destination.equals(route.getDestination())){
				groupMissingPlaces = createTemporaryProcess(route);
				groupMissingPlaces.adicionarLugar(route.getDestination());
				break;
			}
			RoutesGroupVO agrupamentoProcessado = processRoutes(route.getDestination(),destination,hashMapOriginRoutes,groupMissingPlaces);
			if(agrupamentoProcessado.getTotalPercorrido() < groupRoutes.getTotalPercorrido())
			{
				groupRoutes = createTemporaryProcess(route);
				groupRoutes.addDistance(agrupamentoProcessado.getTotalPercorrido());
				groupRoutes.adicionarLugares(agrupamentoProcessado.getLugaresPercorridos());
			}
		}
		
		if(groupMissingPlaces.getTotalPercorrido() < groupRoutes.getTotalPercorrido())
		{
			return groupMissingPlaces;	
		}
		return groupRoutes;
	}

	private RoutesGroupVO createTemporaryProcess(Route route) {
		RoutesGroupVO process = new RoutesGroupVO();
		process.adicionarLugar(route.getOrigin());
		process.addDistance(route.getDistance());
		return process;
	}

	private double calculateCost(int totalDistance, int autonomy, double gasCost) {
		double cost = gasCost * totalDistance /autonomy;
		return  com.wallmart.util.Utils.round(cost);
	}
	
	private HashMap<String, List<Route>> convertToHashMap(List<Route> routes) {
		HashMap<String, List<Route>> hashMap = new HashMap<String, List<Route>>();
		for (Route route : routes) {
			String key = route.getOrigin();
			if(!hashMap.containsKey(key)){
				hashMap.put(key, new ArrayList<Route>());
			}
			hashMap.get(key).add(route);
		}
		return hashMap;
	}

}
