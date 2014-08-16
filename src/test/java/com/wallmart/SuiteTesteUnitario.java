package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wallmart.converters.EntregaJSONConverterTest;
import com.wallmart.converters.MapaJSONConverterTest;
import com.wallmart.service.EntregaServiceImplTest;
import com.wallmart.validators.EntregaControllerValidatorTest;
import com.wallmart.validators.MapaControllerValidatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MapaControllerValidatorTest.class,
	MapaJSONConverterTest.class,
	EntregaControllerValidatorTest.class,
	EntregaServiceImplTest.class,
	EntregaJSONConverterTest.class
})
public class SuiteTesteUnitario {

}
