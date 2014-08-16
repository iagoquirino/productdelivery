package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wallmart.controller.EntregaControllerTest;
import com.wallmart.controller.MapaControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MapaControllerTest.class,
	EntregaControllerTest.class
})
public class SuiteController {
  
}
