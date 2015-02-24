package springbook.learningtest.jdk;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author wglee21g@gmail.com
 */
public class ReflectionTest {
	@Test
	public void invokeMethod() throws Exception {
		String name = "Spring";

		assertThat(name.length(), is(6));

		Method lengthMethod = String.class.getMethod("length");
		assertThat((Integer)lengthMethod.invoke(name), is(6));
		
		assertThat(name.charAt(0), is('S'));

		Method charAtMethod = String.class.getMethod("charAt", int.class);
		assertThat((Character)charAtMethod.invoke(name, 0), is('S'));
	}
}
