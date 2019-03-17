# logback 的配置

- logback和log4j比较，前者是后者改良，logback配置详解(logback取代log4j的理由) : https://blog.csdn.net/thinktothings/article/details/88613500



## 控制台直接打印输出,用默认的配置文件
- pom.xml文件中加入slf4j-api,logback-core,logback-classic,logback-access

### pom.xml

```aidl
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.opensourceteams</groupId>
  <artifactId>logback</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>logback</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <project.logback.version>1.2.3</project.logback.version>
  </properties>

  <dependencies>


    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.26</version>
    </dependency>


    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-core</artifactId>
      <version>${project.logback.version}</version>
    </dependency>

    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>${project.logback.version}</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-access</artifactId>
      <version>${project.logback.version}</version>
    </dependency>




    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <resources>
      <resource><directory>src/main/resources</directory></resource>
    </resources>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <!-- clean lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- default lifecycle, jar packaging: see https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        <!-- site lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>


```

### java 打印日志输出

```aidl
package com.opensourceteams.module.java.loback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run {


    static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {

        logger.info("INFO信息");
        logger.debug("DEBUG信息");
    }
}

```



### java 打印日志输出(日志中输出logback加载信息)

```aidl

package com.opensourceteams.module.java.loback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run2 {


    static Logger logger = LoggerFactory.getLogger(Run2.class);

    public static void main(String[] args) {

        logger.info("INFO信息");
        logger.debug("DEBUG信息");


        //打印 Logback 内部状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}


```



## logback.xml配置文件中配置控制台输了格式

### logback.xml
```aidl
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <!--<pattern>%d{yyyy-MM-dd HH:mm:ss} [%p][%c][%M][%L]-> %m%n</pattern>-->
            <!--
             %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
                 输出类似：2002年10月18日 22：10：28，921
             %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
             %c   输出所属的类目，通常就是所在类的全名
             %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
             %t 输出产生该日志事件的线程名
             %m 输出代码中指定的消息
             %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
             -->
            <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
        </encoder>
    </appender>

    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>


```


## 文件输出配置 logs/logFile.log

```aidl
<configuration>

    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/testFile.log</file>
        <append>true</append>


        <encoder>
            <!--
             %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
                 输出类似：2002年10月18日 22：10：28，921
             %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
             %c   输出所属的类目，通常就是所在类的全名
             %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
             %t 输出产生该日志事件的线程名
             %m 输出代码中指定的消息
             %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
             -->
            <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
        </encoder>
    </appender>

    <root level="DEBUG">
        <appender-ref ref="FILE" />
    </root>
</configuration>


```


## 按日期滚动日志文件，每天一个文件，保留30天，日志最大容量3GB

```aidl




<configuration>
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logs/logFile.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- daily rollover -->
        <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>

        <!-- keep 30 days' worth of history capped at 3GB total size -->
        <maxHistory>30</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>

    </rollingPolicy>

    <encoder>
        <!--
          %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
              输出类似：2002年10月18日 22：10：28，921
          %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
          %c   输出所属的类目，通常就是所在类的全名
          %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
          %t 输出产生该日志事件的线程名
          %m 输出代码中指定的消息
          %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
          -->
        <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
    </encoder>
</appender>

<root level="DEBUG">
    <appender-ref ref="FILE" />
</root>
</configuration>


```



## 按日期滚动日志文件，每天一个文件，保留30天，每个日志文件最大100MB,大于就切分成新文件，日志最大容量3GB


```aidl



<configuration>
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logs/logFile.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
        <!-- daily rollover -->
        <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>

        <!-- each file should be at most 100MB, keep 60 days worth of history, but at most 20GB -->
        <maxFileSize>100MB</maxFileSize>
        <!-- keep 30 days' worth of history capped at 3GB total size -->
        <maxHistory>30</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>

    </rollingPolicy>

    <encoder>
        <!--
          %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
              输出类似：2002年10月18日 22：10：28，921
          %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
          %c   输出所属的类目，通常就是所在类的全名
          %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
          %t 输出产生该日志事件的线程名
          %m 输出代码中指定的消息
          %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
          -->
        <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
    </encoder>
</appender>

<root level="DEBUG">
    <appender-ref ref="FILE" />
</root>
</configuration>



```



## 分离debug,error文件
- logback.xml

```aidl




<configuration>



    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <!--<pattern>%d{yyyy-MM-dd HH:mm:ss} [%p][%c][%M][%L]-> %m%n</pattern>-->
            <!--
             %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
                 输出类似：2002年10月18日 22：10：28，921
             %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
             %c   输出所属的类目，通常就是所在类的全名
             %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
             %t 输出产生该日志事件的线程名
             %m 输出代码中指定的消息
             %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
             -->
            <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
        </encoder>
    </appender>


    <appender name="ErrorFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/errorLogFile.log</file>
        <append>true</append>
        <charset>UTF-8</charset>

        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>


        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- daily rollover -->
            <fileNamePattern>errorLogFile.%d{yyyy-MM-dd}.log</fileNamePattern>

            <!-- keep 30 days' worth of history capped at 3GB total size -->
            <maxHistory>30</maxHistory>
            <totalSizeCap>3GB</totalSizeCap>

        </rollingPolicy>

        <encoder>
            <!--
              %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
                  输出类似：2002年10月18日 22：10：28，921
              %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
              %c   输出所属的类目，通常就是所在类的全名
              %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
              %t 输出产生该日志事件的线程名
              %m 输出代码中指定的消息
              %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
              -->
            <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
        </encoder>
    </appender>



    <appender name="DebugFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/debugFile.log</file>
        <append>true</append>
        <charset>UTF-8</charset>

        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>


        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- daily rollover -->
            <fileNamePattern>debugFile.%d{yyyy-MM-dd}.log</fileNamePattern>

            <!-- keep 30 days' worth of history capped at 3GB total size -->
            <maxHistory>30</maxHistory>
            <totalSizeCap>3GB</totalSizeCap>

        </rollingPolicy>

        <encoder>
            <!--
              %d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss,SSS}，
                  输出类似：2002年10月18日 22：10：28，921
              %p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
              %c   输出所属的类目，通常就是所在类的全名
              %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java:10)
              %t 输出产生该日志事件的线程名
              %m 输出代码中指定的消息
              %n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
              -->
            <pattern>%d{HH:mm:ss,SSS} [%p][%c][%t]%L -> %m%n</pattern>
        </encoder>
    </appender>


    <root level="DEBUG">

        <appender-ref ref="STDOUT" />
        <appender-ref ref="ErrorFile" />
        <appender-ref ref="DebugFile" />
    </root>
</configuration>



```