package org.ace.secondarysort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 分区函数，按key分发
 * @author Liangsj
 *
 */
public class FirstPartitioner extends Partitioner<IntPair, IntWritable>{

	@Override
	public int getPartition(IntPair key, IntWritable value, int numPartitions) {
		  return key.getFirst() % numPartitions;
	}

}
