package org.ace.secondarysort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<IntPair, IntWritable, IntWritable, IntWritable> {

	private IntWritable keyout = new IntWritable();
	
	@Override
	protected void reduce(IntPair key, Iterable<IntWritable> values,
			Reducer<IntPair, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		keyout.set(key.getFirst());
		for(IntWritable val : values) {
			context.write(keyout, val);
		}
	}

	
}
