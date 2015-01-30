package step03.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author wglee21g@gmail.com
 */
public interface LineCallback {
	Integer doSomethingWithReader(String line, Integer result) throws IOException;
}
