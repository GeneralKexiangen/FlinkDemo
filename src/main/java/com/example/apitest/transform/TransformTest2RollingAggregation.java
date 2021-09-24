package com.example.apitest.transform;

import com.example.apitest.beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformTest2RollingAggregation {


    public static void main(String[] args) throws Exception{
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //获取数据源
        DataStream<String> inputStream = env.readTextFile("/Users/zhiyue/IdeaProjects/FlinkDemo/src/main/resources/sensor.txt");

//        DataStream<SensorReading> dataStream = inputStream.map(new MapFunction<String, SensorReading>() {
//            @Override
//            public SensorReading map(String s) throws Exception {
//                String [] fields = s.split(" ");
//                return new SensorReading(fields[0],new Long(fields[1]),new Double(fields[2]));
//            }
//        });

        //lambda表达式
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
                    String[] fields = line.split(" ");
                    return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
                });

        //分组
        KeyedStream<SensorReading, Tuple> keyStream = dataStream.keyBy("sensorId");
        //滚动聚合，去当前最大温度值
        DataStream<SensorReading> temperatureMax = keyStream.max("temperature");
        temperatureMax.print();
        env.execute();

    }
}
