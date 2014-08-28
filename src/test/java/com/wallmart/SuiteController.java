package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wallmart.controller.DeliveryControllerTest;
import com.wallmart.controller.MapControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MapControllerTest.class,
	DeliveryControllerTest.class
})
public class SuiteController {
  
}
