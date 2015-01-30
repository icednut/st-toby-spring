package step04.learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author wglee21g@gmail.com
 */
public class CalculatorTest {
	private Calculator calculator;
	private String filepath;
	private String stringFilepath;

	@Before
	public void setup() throws IOException {
		calculator = new Calculator();
		filepath = getClass().getClassLoader().getResource("numbers.txt").getPath();
		stringFilepath = getClass().getClassLoader().getResource("strings.txt").getPath();
	}

	@Test
	public void testCalcSum() throws Exception {
		assertThat(calculator.calcSum(filepath), is(10));
	}

	@Test
	public void testCalcMultiply() throws Exception {
		assertThat(calculator.calcMultiply(filepath), is(24));
	}

	@Test
	public void testStringSum() throws Exception {
		assertThat(calculator.stringSum(stringFilepath), is("HelloWorld!"));

	}
}
