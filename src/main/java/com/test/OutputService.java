package com.test;

import java.io.File;
import java.io.PrintWriter;

/**
 * @Author: Rong.Li
 * @Date: 2019/7/12 16:24
 * @Description:将输入的两个参数通过IO存入文件
 */
public class OutputService {
    public static void output(String filename,String a,String b) throws Exception{
        File file = new File("C:\\apache-jmeter-5.1.1\\testData\\");
        if (!file.exists()){
            file.mkdir();//创建文件夹
        }
        PrintWriter out = new PrintWriter(new File("C:\\apache-jmeter-5.1.1\\testData\\"+filename));
        out.write(a+":"+b);
        out.close();
    }
}
