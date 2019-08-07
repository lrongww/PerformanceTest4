package com.test;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @Author: Rong.Li
 * @Date: 2019/7/12 16:33
 * @Description:测试类
 */
public class PerformenceTest implements JavaSamplerClient {
    private SampleResult results;
    private String a;
    private String b;
    private String filename;
    //设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
    @Override
    public Arguments getDefaultParameters(){
        Arguments params = new Arguments();
        params.addArgument("a","0");
        params.addArgument("b","0");
        params.addArgument("filename","0");//默认值为0
        return params;
    }
    //初始化方法，在测试方法运行前执行
    public void setupTest(JavaSamplerContext arg0){
        results = new SampleResult();

    }
    //测试执行的循环体，根据线程数和循环次数的不同可执行多次
    @Override
    public SampleResult runTest(JavaSamplerContext arg0){
        b = arg0.getParameter("b");//获取jmeter中设置的参数
        a = arg0.getParameter("a");
        filename = arg0.getParameter("filename");
        results.sampleStart();//jmeter开始统计响应时间的标记
        try{
            OutputService test = new OutputService();
            test.output(filename,a,b);
            results.setResponseCode("200");
            results.setResponseMessage("output Successfully");
            results.setResponseData(("a:"+a +" b:"+b+" output "+filename+" Successfully!").getBytes());
            results.setSuccessful(true);
        }catch (Throwable e){
            results.setSuccessful(false);
            results.setResponseData(("error:"+e).getBytes());
            results.setDataType(SampleResult.TEXT);
            e.printStackTrace();
        }finally {
            results.sampleEnd();//jmeter结束统计响应时间标记
        }
        return results;
    }
    //结束方法,在测试方法运行结束后执行
    public void teardownTest(JavaSamplerContext arg0){

    }
    public static void main(String[] args) {
        PerformenceTest test = new PerformenceTest();
        Arguments defaultParameters = test.getDefaultParameters();
        JavaSamplerContext arg0 = new JavaSamplerContext(defaultParameters);
        test.setupTest(arg0);
        test.runTest(arg0);
        test.teardownTest(arg0);
    }
}
