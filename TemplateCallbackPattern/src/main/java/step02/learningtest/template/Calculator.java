package step02.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wglee21g@gmail.com
 */
public class Calculator {
	public Integer calcSum(String filepath) throws IOException {
		return fileReadTemplate(filepath, new BufferedReaderCallback() {
			@Override
			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
				String line = null;
				Integer result = 0;
				while ((line = br.readLine()) != null) {
					result += Integer.valueOf(line);
				}
				return result;
			}
		});
	}

	public int calcMultiply(String filepath) throws IOException {
		return fileReadTemplate(filepath, new BufferedReaderCallback() {
			@Override
			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
				String line = null;
				Integer result = 1;
				while ((line = br.readLine()) != null) {
					result *= Integer.valueOf(line);
				}
				return result;
			}
		});
	}

	private Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filepath));
			return callback.doSomethingWithReader(br);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
