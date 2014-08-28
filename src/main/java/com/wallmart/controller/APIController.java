package com.wallmart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wallmart.exception.APIException;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.rest.json.MessageJSON;

public class APIController {

	@ExceptionHandler(value=APIException.class)
	@ResponseBody
	protected MessageJSON processAPIError(APIException exception, HttpServletResponse response) throws IOException{
		response.setStatus(exception.getHttpStatus().value());
		response.setContentType("application/json");
		return exception.getMessageJSON();
    }
	
	@ExceptionHandler(value=ProductDeliveryException.class)
	@ResponseBody
    protected MessageJSON processBusinessError(ProductDeliveryException exception, HttpServletResponse response) throws IOException{
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setContentType("application/json");
		return new MessageJSON(exception.getMessage());
    }
	
	protected String toJSON(Object object)
	{
		return new Gson().toJson(object);
	}
}
