package com.example.apitest.source;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SourceTest2File {

    public static void main(String[] args) throws Exception{

        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //从文件中读取数据
        DataStream<String> dataStream = env.readTextFile("/Users/zhiyue/IdeaProjects/FlinkDemo/src/main/resources/hello.txt");

        //打印输出
        dataStream.print("fromFile");

        //执行
        env.execute();

    }
}
