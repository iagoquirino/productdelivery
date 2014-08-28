package com.wallmart.converters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wallmart.model.vo.DeliveryVO;
import com.wallmart.model.vo.PointVO;
import com.wallmart.rest.json.DeliveryJSON;
import com.wallmart.rest.json.PointJSON;

public class DeliveryJSONConverterTest {

	DeliveryJSONConverter deliveryJSONConverter = new DeliveryJSONConverter();

	@Test
	public void convertToJSON()
	{
		DeliveryVO delivery = getMockDelivery();
		DeliveryJSON deliveryJSON = deliveryJSONConverter.convertToJSON(delivery);
		Assert.assertEquals(delivery.getCost()+"", delivery.getCost()+"");
		Assert.assertEquals(delivery.getPoints().size(), deliveryJSON.getPoints().size());
		assertJSON(delivery.getPoints().get(0), deliveryJSON.getPoints().get(0));
		assertJSON(delivery.getPoints().get(1), deliveryJSON.getPoints().get(1));
	}

	private void assertJSON(PointVO ponto, PointJSON pontoJSON) {
		Assert.assertEquals(ponto.getName(), pontoJSON.getName());
	}

	private DeliveryVO getMockDelivery() {
		return new DeliveryVO(2.9, getMockPoints());
	}

	private List<PointVO> getMockPoints() {
		return Arrays.asList(new PointVO("test"),new PointVO("test2"));
	}
	
}
