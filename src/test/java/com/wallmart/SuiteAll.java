package com.wallmart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SuiteController.class,
	SuiteTesteUnitario.class,
	SuiteIntegration.class
})
public class SuiteAll {

}
