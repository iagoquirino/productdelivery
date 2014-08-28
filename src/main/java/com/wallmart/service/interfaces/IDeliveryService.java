package com.wallmart.service.interfaces;

import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;
import com.wallmart.model.vo.DeliveryVO;

public interface IDeliveryService {

	DeliveryVO calculateRoutes(Map map, String origin, String destination,Integer autonomy, Double gasCost) throws ProductDeliveryException;

}
