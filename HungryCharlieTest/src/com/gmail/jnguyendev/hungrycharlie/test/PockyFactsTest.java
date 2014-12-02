package com.gmail.jnguyendev.hungrycharlie.test;

import android.test.AndroidTestCase;

import com.gmail.jnguyendev.hungrycharlie.PockyFacts;

public class PockyFactsTest extends AndroidTestCase {

	private static final int REPEAT_ITERATIONS = 100000;
	private PockyFacts facts;
	
	public void setUp() {
		facts = PockyFacts.newInstance();
	}

	public void tearDown() { }

	public void testNoRepeats() {
		String cur, prev = "";
		int repeatCount = 0;
		for (int i = 0; i < REPEAT_ITERATIONS; i++) {
			cur = facts.getFact();
			if (prev.equals(cur)) {
				repeatCount++;
			}
			prev = cur;
		}

		assert((facts.getCount() * repeatCount / REPEAT_ITERATIONS) < 2);
	}
	
}
