package com.wallmart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wallmart.exception.APIException;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.rest.json.MensagemJSON;

public class APIController {

	@ExceptionHandler(value=APIException.class)
	@ResponseBody
	protected MensagemJSON tratarErroAPI(APIException exception, HttpServletResponse response) throws IOException{
		response.setStatus(exception.getHttpStatus().value());
		response.setContentType("application/json");
		return exception.getMensagemJSON();
    }
	
	@ExceptionHandler(value=EntregaMercadoriaException.class)
	@ResponseBody
    protected MensagemJSON tratarErroService(EntregaMercadoriaException exception, HttpServletResponse response) throws IOException{
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setContentType("application/json");
		return new MensagemJSON(exception.getMensagem());
    }
	
	protected String toJSON(Object object)
	{
		return new Gson().toJson(object);
	}
}
