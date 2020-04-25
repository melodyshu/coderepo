要想设置tag的值，必须开启以下项

hive-site.xml

```xml
<property>
     <name>hive.security.authorization.sqlstd.confwhitelist.append</name>
     <value>hive.*|mapred.*|tez.*|queue.*</value>
</property>
<property>
     <name>hive.security.authorization.sqlstd.confwhitelist</name>
     <value>hive.*|mapred.*|tez.*|queue.*</value>
</property>
```



![image-20200425090214728](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200425090214728.png)

否则会报如下错误:
java.sql.SQLException: Could not open client transport with JDBC Uri: jdbc:hive2://192.168.230.163:10000/default: Failed to open new session: java.lang.IllegalArgumentException: Cannot modify tez.application.tags at runtime. It is not in list of params that are allowed to be modified at runtime
	at org.apache.hive.jdbc.HiveConnection.<init>(HiveConnection.java:252)
	at org.apache.hive.jdbc.HiveDriver.connect(HiveDriver.java:107)
	at java.sql.DriverManager.getConnection(DriverManager.java:664)
	at java.sql.DriverManager.getConnection(DriverManager.java:208)
	at org.example.YarnAppIdQuery.getHiveStatement(YarnAppIdQuery.java:81)
	at org.example.YarnAppIdQuery.query(YarnAppIdQuery.java:31)
	at org.example.YarnAppIdQuery.main(YarnAppIdQuery.java:24)
Caused by: org.apache.hive.service.cli.HiveSQLException: Failed to open new session: java.lang.IllegalArgumentException: Cannot modify tez.application.tags at runtime. It is not in list of params that are allowed to be modified at runtime
	at org.apache.hive.jdbc.Utils.verifySuccess(Utils.java:300)
	at org.apache.hive.jdbc.Utils.verifySuccess(Utils.java:291)
	at org.apache.hive.jdbc.HiveConnection.openSession(HiveConnection.java:718)
	at org.apache.hive.jdbc.HiveConnection.<init>(HiveConnection.java:228)
	... 6 more
Caused by: org.apache.hive.service.cli.HiveSQLException: Failed to open new session: java.lang.IllegalArgumentException: Cannot modify tez.application.tags at runtime. It is not in list of params that are allowed to be modified at runtime
	at org.apache.hive.service.cli.session.SessionManager.createSession(SessionManager.java:451)
	at org.apache.hive.service.cli.session.SessionManager.openSession(SessionManager.java:382)
	at org.apache.hive.service.cli.CLIService.openSessionWithImpersonation(CLIService.java:198)
	at org.apache.hive.service.cli.thrift.ThriftCLIService.getSessionHandle(ThriftCLIService.java:472)
	at org.apache.hive.service.cli.thrift.ThriftCLIService.OpenSession(ThriftCLIService.java:322)
	at org.apache.hive.service.rpc.thrift.TCLIService$Processor$OpenSession.getResult(TCLIService.java:1497)
	at org.apache.hive.service.rpc.thrift.TCLIService$Processor$OpenSession.getResult(TCLIService.java:1482)
	at org.apache.thrift.ProcessFunction.process(ProcessFunction.java:39)
	at org.apache.thrift.TBaseProcessor.process(TBaseProcessor.java:39)
	at org.apache.hive.service.auth.TSetIpAddressProcessor.process(TSetIpAddressProcessor.java:56)
	at org.apache.thrift.server.TThreadPoolServer$WorkerProcess.run(TThreadPoolServer.java:286)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: org.apache.hive.service.cli.HiveSQLException: java.lang.IllegalArgumentException: Cannot modify tez.application.tags at runtime. It is not in list of params that are allowed to be modified at runtime
	at org.apache.hive.service.cli.session.HiveSessionImpl.configureSession(HiveSessionImpl.java:289)
	at org.apache.hive.service.cli.session.HiveSessionImpl.open(HiveSessionImpl.java:199)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.hive.service.cli.session.HiveSessionProxy.invoke(HiveSessionProxy.java:78)
	at org.apache.hive.service.cli.session.HiveSessionProxy.access$000(HiveSessionProxy.java:36)
	at org.apache.hive.service.cli.session.HiveSessionProxy$1.run(HiveSessionProxy.java:63)
	at java.security.AccessController.doPrivileged(Native Method)
	at javax.security.auth.Subject.doAs(Subject.java:422)
	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1730)
	at org.apache.hive.service.cli.session.HiveSessionProxy.invoke(HiveSessionProxy.java:59)
	at com.sun.proxy.$Proxy65.open(Unknown Source)
	at org.apache.hive.service.cli.session.SessionManager.createSession(SessionManager.java:442)
	... 13 more
Caused by: java.lang.IllegalArgumentException: Cannot modify tez.application.tags at runtime. It is not in list of params that are allowed to be modified at runtime
	at org.apache.hadoop.hive.conf.HiveConf.verifyAndSet(HiveConf.java:4737)
	at org.apache.hadoop.hive.ql.processors.SetProcessor.setConf(SetProcessor.java:260)
	at org.apache.hadoop.hive.ql.processors.SetProcessor.setConf(SetProcessor.java:223)
	at org.apache.hadoop.hive.ql.processors.SetProcessor.setVariable(SetProcessor.java:193)
	at org.apache.hive.service.cli.session.HiveSessionImpl.configureSession(HiveSessionImpl.java:287)
	... 27 more
Exception in thread "main" java.lang.NullPointerException
	at org.example.YarnAppIdQuery.getHiveStatement(YarnAppIdQuery.java:86)
	at org.example.YarnAppIdQuery.query(YarnAppIdQuery.java:31)
	at org.example.YarnAppIdQuery.main(YarnAppIdQuery.java:24)
Disconnected from the target VM, address: '127.0.0.1:56831', transport: 'socket'