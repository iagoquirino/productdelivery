package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wallmart.controller.MapControllerIntegrationTest;
import com.wallmart.service.MapServiceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CleanAll.class,
	MapControllerIntegrationTest.class,
	MapServiceIntegrationTest.class
})
public class SuiteIntegration {

}
