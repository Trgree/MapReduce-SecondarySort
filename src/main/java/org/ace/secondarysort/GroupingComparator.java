package org.ace.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 分组函数类。只要first相同就属于同一个组。
 * @author Liangsj
 *
 */
public class GroupingComparator extends WritableComparator{

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable a, WritableComparable  b) {
		IntPair ip1 = (IntPair)a;
		IntPair ip2 = (IntPair)b;
		int i1 = ip1.getFirst();
		int i2 = ip2.getFirst();
		return i1 > i2 ? 1 : (i1 < i2 ? -1 : 0);
	}

	public GroupingComparator() {
		super(IntPair.class, true);
	}
	
}

