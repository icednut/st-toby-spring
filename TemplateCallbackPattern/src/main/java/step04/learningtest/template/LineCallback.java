package step04.learningtest.template;

import java.io.IOException;

/**
 * @author wglee21g@gmail.com
 */
public interface LineCallback<T> {
	T doSomethingWithReader(String line, T result) throws IOException;
}
