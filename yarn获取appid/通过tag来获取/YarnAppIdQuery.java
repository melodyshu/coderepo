package org.example;

import com.google.common.collect.Sets;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hive.jdbc.HiveStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

/**
 * 需要hortonworks仓库
 * 需要hive-jdbc和服务器保持版本一致
 * 需要hadoop-common和服务器保持版本一致
 */
public class YarnAppIdQuery
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        query();
    }

    public static void query() throws Exception {
        String sql="insert into table mytest values(100,'aaa')";
        //每次执行产生唯一tag
        String yarntag="hive-client-job-test-06";
        HiveStatement hiveStatement = getHiveStatement(sql,yarntag);
        YarnConfiguration conf = new YarnConfiguration();
        conf.set("yarn.resourcemanager.address","192.168.230.163:8050");
        YarnConfiguration configuration = new YarnConfiguration(conf);
        YarnClient yarnClient = YarnClient.createYarnClient();
        yarnClient.init(configuration);
        yarnClient.start();

        Set<String> applicationTypes = Sets.newHashSet();
        applicationTypes.add("tez");

        //要获取yarn任务列表，按照状态过滤
        EnumSet<YarnApplicationState> applicationStates = EnumSet.noneOf(YarnApplicationState.class);
        applicationStates.add(YarnApplicationState.ACCEPTED);
        applicationStates.add(YarnApplicationState.SUBMITTED);
        applicationStates.add(YarnApplicationState.RUNNING);
        applicationStates.add(YarnApplicationState.FINISHED);
        applicationStates.add(YarnApplicationState.NEW);
        applicationStates.add(YarnApplicationState.NEW_SAVING);
        applicationStates.add(YarnApplicationState.FAILED);
        applicationStates.add(YarnApplicationState.KILLED);

        List<ApplicationReport> reports = yarnClient.getApplications(applicationTypes, applicationStates);

        for (ApplicationReport report : reports) {
            System.out.println("============="+report.getApplicationId());
        }
        int updateRows = hiveStatement.executeUpdate(sql);

        Set<String> applicationTags = new HashSet<>();
        //和前面的tag保持一致,获取指定tag的yarn appid
        applicationTags.add(yarntag);
        reports = yarnClient.getApplications(applicationTypes, applicationStates,applicationTags);
        for (ApplicationReport report : reports) {
            System.out.println("***************"+report.getApplicationId());
        }
    }

    private static HiveStatement getHiveStatement(String sql,String yarntag) throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        String url = "jdbc:hive2://192.168.230.163:10000/default";
        String username = "hive";
        String password = null;
        Connection conn=null;
        try {
            Properties info = new Properties();
            info.setProperty("user", username);
            info.setProperty("hiveconf:hive.execution.engine", "tez");
            //设定hive提交给yarn任务的tags
            info.setProperty("hiveconf:tez.application.tags", yarntag);
            conn = DriverManager.getConnection(url,info);
        }catch (Exception e){
            e.printStackTrace();
        }

        HiveStatement stmt = (HiveStatement) conn.createStatement();
        return stmt;
    }
}
