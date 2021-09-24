package com.example.apitest.source;

import com.example.apitest.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import java.util.Arrays;

public class SourceTest1Collection {

    public static void main(String[] args) throws Exception {
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        //从集合中读取数据
        DataStreamSource<SensorReading> dataStream = env.fromCollection(Arrays.asList(
                new SensorReading("sensor_1", 1622097671L, 36.1),
                new SensorReading("sensor_2", 1622097672L, 30.1),
                new SensorReading("sensor_3", 1622097673L, 23.1),
                new SensorReading("sensor_4", 1622097674L, 48.1)
        ));

        DataStreamSource<Integer> intData = env.fromElements(1, 2, 5, 666, 333, 4567);

        //打印输出数据
        dataStream.print("fromCollection");
        intData.print("fromElement");

        //执行
        env.execute();
    }
}
