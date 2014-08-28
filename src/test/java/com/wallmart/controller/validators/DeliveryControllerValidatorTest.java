package com.wallmart.controller.validators;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;

public class DeliveryControllerValidatorTest {

	private static final double INVALID_GAS_COST = -1.0;

	private static final int AUTONOMY = 1;

	private static final double GAS_COST = 2.2;

	private static final String ORIGIN = "ORIGIN";

	private static final String DESTINY = "DESTINATION";
	
	DeliveryControllerValidator destinyControllerValidator = new DeliveryControllerValidator();
	
	@Test
	public void validateInvalidOrigin(){
		try{
			destinyControllerValidator.validate("", DESTINY, AUTONOMY, GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_ORIGIN_ROUTE);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void validateNullOrigin(){
		try{
			destinyControllerValidator.validate(null, DESTINY, AUTONOMY, GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_ORIGIN_ROUTE);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void validateInvalidDestination(){
		try{
			destinyControllerValidator.validate(ORIGIN, "", AUTONOMY, GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_DESTINATION_ROUTE);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void validateNullDestination(){
		try{
			destinyControllerValidator.validate(ORIGIN, null, AUTONOMY, GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_DESTINATION_ROUTE);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void validateInvalidAutonomy(){
		try{
			destinyControllerValidator.validate(ORIGIN, DESTINY, 0, GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_AUTONOMY);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void validateNullAutonomy(){
		try{
			destinyControllerValidator.validate(ORIGIN, DESTINY, null, GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_AUTONOMY);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}

	@Test
	public void validateInvalidGasCost(){
		try{
			destinyControllerValidator.validate(ORIGIN, DESTINY, AUTONOMY, INVALID_GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_GAS_COST);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void validateNullGasCost(){
		try{
			destinyControllerValidator.validate(ORIGIN, DESTINY, AUTONOMY, INVALID_GAS_COST);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.INVALID_GAS_COST);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void dontValidate(){
		destinyControllerValidator.validate(ORIGIN, DESTINY, AUTONOMY, 0.0);
	}

	
	
}
