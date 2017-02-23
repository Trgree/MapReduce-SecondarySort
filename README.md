# MapReduce-SecondarySort
MapReduce二次排序例子


###二次排序
(先按第一列排序，再按第二列排序)
####实现原理：
    map分发数据到reduce时，就会按key排序，即调用key的compareTo方法，
	
    只要把第一个数相同的行发到同一个reduce且重写每行间数的大小比较即可
  
* 定义一个新key(IntPair)，由要排序的两个数组成
* map输出&lt;IntPair,key&gt;
* 重写Partitioner,第一个数一样的，发送到同一个reduce
* 重写WritableComparator，按第一个数分组，（IntPair中也要重写compareTo方法）


 
输入：

```
5|67
4|5
4|3
```

输出：

```
4|3
4|5
5|67
```
