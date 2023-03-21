package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

class wtest {
	File hello = new File("hello.txt");
	static LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
	
	

	@Test
	void test() {
		lhm.put("hello", 2);
		lhm.put("you", 1);
		lhm.put("are", 1);
		
		Tutorial test = new Tutorial();
		LinkedHashMap<String, Integer> output = test.wordOccurrence(hello);
		assertEquals(lhm, output);
	}

}
