#!/bin/bash
#Created by lsj 2017-2-15

DATE=`date +"%Y%m%d"`

#LOCAL
WORKPATH=/home/bigdata/project/lsj/secondarySort
JAVALIB=${WORKPATH}/mr
LOGFILE=${WORKPATH}/log/secondarySort_${DATE}.log
MRJAR=$JAVALIB/secondarySort.jar

ARGS="
-D input=/tmp/secondarySort/input 
-D output=/tmp/secondarySort/ouput 
-D mapreduce.job.reduces=1
"

echo "DATE" ${DATE}
echo "WORKPATH" ${WORKPATH}
echo "LOGFILE" ${LOGFILE}
echo "INPUT" ${INPUT}
echo "OUTPUT" ${OUTPUT}
echo "MRJAR" ${MRJAR}

echo `date +"%Y-%m-%d %H:%M:%S"`  "Start..."  | tee -a $LOGFILE

hadoop jar ${MRJAR} org.ace.secondarysort.SortDriver $ARGS  1>>$LOGFILE 2>>$LOGFILE

ret=$?
if [ ${ret} -ne 0 ]; then
     exit ${ret}
fi

echo `date +"%Y-%m-%d %H:%M:%S"`    "Exec Success [$DATE]!" | tee -a $LOGFILE

exit 0
