package com.wallmart.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class BaseControllerTest {

    private MockMvc mockMvc;
    
    public void init(APIController controller){
    	 this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    public void initIntegration(WebApplicationContext webApplicationContext){
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    public MockMvc getMockMvc(){
    	return this.mockMvc;
    }
}
