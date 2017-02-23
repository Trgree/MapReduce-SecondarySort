package org.ace.secondarysort;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 二次排序
 * 先按第一列排序，再按第二列排序
 * 实现原理：
 * 		map分发数据到reduce时，就会按key排序，即调用key的compareTo方法，
 *  只要把第一个数相同的行发到同一个reduce且重写每行间数的大小比较即可
 * 
 * 		定义一个新key(IntPair)，由要排序的两个数组成，
 * 		map输出<IntPair,key>
 * 		重写Partitioner,第一个数一样的，发送到同一个reduce
 * 		重写WritableComparator，按第一个数分组，（IntPair中也要重写compareTo方法）
 *  	

输入：
5|67
4|5
4|3

输出：
4|3
4|5
5|67

 * @author Liangsj
 */
public class SortDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();

		String input = conf.get("input");
		String output = conf.get("output");

		if (!check(input, "input") || !check(output, "output")) {
			return 1;
		}

		// 如果输出目录已存在，需要删除
		FileSystem fsTarget = FileSystem.get(URI.create(output), conf);
		Path pathTarget = new Path(output);
		if (fsTarget.exists(pathTarget)) {
			fsTarget.delete(pathTarget, true);
		}

		Job job = Job.getInstance(conf);
		job.setJobName("Second Sort");
		job.setJarByClass(getClass());
		job.setMapperClass(SortMapper.class);
		
		job.setGroupingComparatorClass(GroupingComparator.class);
		
		job.setPartitionerClass(FirstPartitioner.class);
		job.setReducerClass(SortReducer.class);
		job.setMapOutputKeyClass(IntPair.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

	private static boolean check(final String value, final String key) {
		System.out.println(key + ":" + value);
		if (value == null) {
			System.err.println("no " + key + " param, Usage: -D " + key + "=xxx");
			System.out.println("Usage: hadoop jar  <jarfile> SortDriver -D input=xxx -D output=xxx -D mapreduce.job.reduces=4");
			return false;
		}
		return true;
	}
	
    public static void main(String[] args)throws Exception{
        int exitcode = ToolRunner.run(new SortDriver(), args);
        System.exit(exitcode);                  
   }


}
