package com.wallmart.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class BaseControllerTest {

    private MockMvc mockMvc;
    
    public void init(APIController controller){
    	 this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    public MockMvc getMockMvc(){
    	return this.mockMvc;
    }
}
