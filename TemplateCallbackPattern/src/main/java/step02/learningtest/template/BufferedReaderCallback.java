package step02.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author wglee21g@gmail.com
 */
public interface BufferedReaderCallback {
	Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
