package com.wallmart.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wallmart.model.vo.DeliveryVO;
import com.wallmart.model.vo.PointVO;
import com.wallmart.rest.json.DeliveryJSON;
import com.wallmart.rest.json.PointJSON;

@Component
public class DeliveryJSONConverter {

	public DeliveryJSON convertToJSON(DeliveryVO delivery) {
		if(delivery == null)
		{
			return null;
		}
		DeliveryJSON deliveryJSON = new DeliveryJSON();
		deliveryJSON.setCost(delivery.getCost());
		List<PointJSON> pointJSON = convertPointsToJSON(delivery.getPoints());
		deliveryJSON.setPoints(pointJSON);
		return deliveryJSON;
	}

	private List<PointJSON> convertPointsToJSON(List<PointVO> points) {
		List<PointJSON> pointsJSON = new ArrayList<PointJSON>();
		if(points != null){
			for(PointVO pointVO : points){
				if(pointVO != null){
					PointJSON pointJSON = new PointJSON(pointVO.getName());
					pointsJSON.add(pointJSON);
				}
			}
		}
		return pointsJSON;
	}

}
