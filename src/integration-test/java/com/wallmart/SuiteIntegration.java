package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wallmart.controller.MapaControllerIntegrationTest;
import com.wallmart.service.MapaServiceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CleanAll.class,
	MapaControllerIntegrationTest.class,
	MapaServiceIntegrationTest.class
})
public class SuiteIntegration {

}
