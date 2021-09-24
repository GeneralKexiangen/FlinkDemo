package com.example.apitest.source;

import com.example.apitest.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.HashMap;
import java.util.Random;

public class SourceTest4UDf {


    public static void main(String[] args) throws Exception{
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //从自定义的数据集中获取数据

        DataStream<SensorReading> dataStream = env.addSource(new MySensorSource());

        //打印输出
        dataStream.print();

        //执行
        env.execute();
    }


    public static class MySensorSource implements SourceFunction<SensorReading>{
        private boolean running = true;
        @Override
        public void run(SourceContext<SensorReading> sourceContext) throws Exception {
            HashMap<String, Double> sensorTempMap = new HashMap<>();
            Random random = new Random();
            for(int i =0;i<10;i++){
                sensorTempMap.put("sensor_"+(i+1),60+random.nextGaussian()*20);
            }

            while (running){
                for(String key: sensorTempMap.keySet()){
                    Double newTemp = sensorTempMap.get(key)+ random.nextGaussian();
                    sensorTempMap.put(key,newTemp);
                    sourceContext.collect(new SensorReading(key,System.currentTimeMillis(),newTemp));
                }
            }
        }

        @Override
        public void cancel() {
            running = false;
        }
    }
}
