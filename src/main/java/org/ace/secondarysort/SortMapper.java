package org.ace.secondarysort;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<LongWritable, Text, IntPair, IntWritable> {

	private IntPair intPair = new IntPair();
	private IntWritable keyout = new IntWritable();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntPair, IntWritable>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString(), "|");
		int left = 0;
		int right = 0;
		if(st.hasMoreTokens()) {
			left = Integer.parseInt(st.nextToken());
			if(st.hasMoreTokens()){
				right = Integer.parseInt(st.nextToken());
			}
			intPair.set(left, right);
			keyout.set(right);
			context.write(intPair, keyout);
		}
		
	}
	

}
