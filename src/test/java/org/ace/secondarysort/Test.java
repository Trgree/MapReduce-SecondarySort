package org.ace.secondarysort;

import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		IntPair in = new IntPair();
		in.setFirst(2);
		in.setSecond(2);
		
		IntPair in2 = new IntPair();
		in2.setFirst(2);
		in2.setSecond(3);
		
		System.out.println(in.compareTo(in2));
		
		StringTokenizer st = new StringTokenizer("sdf|df".toString(), "|");
		System.out.println(st.nextToken());
		System.out.println(st.nextToken());
	}
}
