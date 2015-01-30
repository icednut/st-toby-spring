package step01.learningtest.template;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author wglee21g@gmail.com
 */
public class CalculatorTest {
	@Test
	public void testCalcSum() throws Exception {
		Calculator calculator = new Calculator();
		int result = calculator.calcSum(getClass().getClassLoader().getResource("numbers.txt").getPath());

		assertThat(result, is(10));
	}

	@Test
	public void testCalcMultiply() throws Exception {
		Calculator calculator = new Calculator();
		int result = calculator.calcMultiply(getClass().getClassLoader().getResource("numbers.txt").getPath());

		assertThat(result, is(24));
	}
}