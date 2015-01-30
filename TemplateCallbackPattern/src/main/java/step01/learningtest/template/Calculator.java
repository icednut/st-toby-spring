package step01.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wglee21g@gmail.com
 */
public class Calculator {
	public Integer calcSum(String filepath) throws IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filepath));
			Integer result = 0;
			String line = null;

			while ((line = br.readLine()) != null) {
				result += Integer.valueOf(line);
			}

			return result;
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

	public int calcMultiply(String filepath)  throws IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filepath));
			Integer result = 1;
			String line = null;

			while ((line = br.readLine()) != null) {
				result *= Integer.valueOf(line);
			}

			return result;
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