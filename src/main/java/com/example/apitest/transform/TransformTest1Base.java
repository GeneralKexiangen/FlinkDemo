package com.example.apitest.transform;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class TransformTest1Base {


    public static void main(String[] args) throws Exception{
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //获取数据源
        DataStream<String> dataStream = env.readTextFile("/Users/zhiyue/IdeaProjects/FlinkDemo/src/main/resources/hello.txt");

        //1.map处理
        DataStream<Object> mapStream = dataStream.map(new MapFunction<String, Object>() {
            @Override
            public Object map(String s) throws Exception {
                return s.length();
            }
        });

        //2.flatMap处理
        DataStream<Object> flatMapStream = dataStream.flatMap(new FlatMapFunction<String, Object>() {
            @Override
            public void flatMap(String s, Collector<Object> collector) throws Exception {
                String[] fields = s.split(" ");
                for (String field : fields) {
                    collector.collect(field);
                }
            }
        });

        //3.filter处理
        DataStream<String> filterStream = dataStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return s.startsWith("and");
            }
        });

        //打印输出
        mapStream.print("map");
        flatMapStream.print("flatMap");
        filterStream.print("filter");

        //执行
        env.execute();
    }
}
