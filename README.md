# Log4j2 Dos Env

漏洞环境仅用于安全研究，禁止非法用途，造成的后果使用者负责

## 第一种情况

在`log4j2.xml`中配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn" name="MyApp" packages="">
    <appenders>
        <console name="STDOUT" target="SYSTEM_OUT">
            <PatternLayout pattern="%msg{lookups}%n"/>
        </console>
    </appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="STDOUT"/>
        </Root>
    </Loggers>
</Configuration>
```

这时候漏洞地址为：`localhost:8080/test?message=payload`

## 第二种情况

在`log4j2.xml`中配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn" name="MyApp" packages="">
    <appenders>
        <console name="STDOUT" target="SYSTEM_OUT">
            <PatternLayout>
                <pattern>%d %p %c{1.} [%t] $${ctx:loginId} %m%n</pattern>
            </PatternLayout>
        </console>
    </appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="STDOUT"/>
        </Root>
    </Loggers>
</Configuration>
```

这时候漏洞地址为：`localhost:8080/cve?userId=payload`
