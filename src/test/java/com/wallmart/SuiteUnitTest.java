package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wallmart.controller.validators.DeliveryControllerValidatorTest;
import com.wallmart.controller.validators.MapControllerValidatorTest;
import com.wallmart.converters.DeliveryJSONConverterTest;
import com.wallmart.converters.MapJSONConverterTest;
import com.wallmart.service.DeliveryServiceImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MapControllerValidatorTest.class,
	MapJSONConverterTest.class,
	DeliveryControllerValidatorTest.class,
	DeliveryServiceImplTest.class,
	DeliveryJSONConverterTest.class
})
public class SuiteUnitTest {

}
