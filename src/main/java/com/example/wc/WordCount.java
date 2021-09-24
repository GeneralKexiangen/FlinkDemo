package com.example.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class WordCount {

    public static void main(String[] args) throws Exception{
        //创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        
        //获取文件数据
        DataSet<String> fileData = env.readTextFile("/Users/zhiyue/IdeaProjects/FlinkDemo/src/main/resources/hello.txt");

        //处理数据计数
        DataSet<Tuple2<String, Integer>> resultData = fileData.flatMap(new MyFlatMapper())
                .groupBy(0)
                .sum(1);

        resultData.print();

    }

    public static class MyFlatMapper implements FlatMapFunction<String,Tuple2<String,Integer>>{

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String [] fields = s.split(" ");
            for(String field:fields){
                collector.collect(new Tuple2<>(field,1));
            }
        }
    }
}
