package cn.itcast.demo4.sortUpCountFlow;

import cn.itcast.demo3.flowCount.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FlowSortMain extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(super.getConf(), "flowSort");

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("file:///F:\\传智播客大数据离线阶段课程资料\\4、大数据离线第四天\\流量统计\\out_sort_flow"));

        job.setMapperClass(FlowSortMapper.class);
        job.setMapOutputKeyClass(FlowBeanSort.class);
        job.setMapOutputValueClass(Text.class);


        job.setReducerClass(FlowSortReducer.class);
        job.setOutputKeyClass(FlowBeanSort.class);
        job.setOutputValueClass(Text.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("file:///F:\\传智播客大数据离线阶段课程资料\\4、大数据离线第四天\\流量统计\\upflow_sort_output"));

        boolean b = job.waitForCompletion(true);


        return b?0:1;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new Configuration(), new FlowSortMain(), args);
        System.exit(run);
    }

}
