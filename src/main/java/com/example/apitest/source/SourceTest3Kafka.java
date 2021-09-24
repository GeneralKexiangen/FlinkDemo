package com.example.apitest.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class SourceTest3Kafka {


    public static void main(String[] args) throws Exception  {

        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //从kafka中读取数据
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","10.10.189.141:2181");


        DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("ct_pu", new SimpleStringSchema(), properties));

        dataStream.print("ct_pu");

        env.execute();

    }
}
