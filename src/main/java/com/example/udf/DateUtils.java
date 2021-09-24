package com.example.udf;
import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.table.functions.ScalarFunction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends ScalarFunction{

    @Override
    public void open(FunctionContext context) throws Exception {
        super.open(context);
    }

    public long eval(String... dateStr){
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(dateStr[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date ==null?0:date.getTime();
    }

    @Override
    public void close() throws Exception {
        super.close();
    }


    public static void main(String[] args) {
        DateUtils dateUtils = new DateUtils();
        System.out.println(dateUtils.eval("2021-09-01 00:00:00","2021-09-01 00:00:00"));
    }

}
