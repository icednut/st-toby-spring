package springbook.learningtest.proxy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author wangeun.lee@sk.com
 */
public class HelloUppercaseTest {
	@Test
	public void proxyTest() {
		Hello proxiedHello = new HelloUppercase(new HelloTarget());

		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
	}
}