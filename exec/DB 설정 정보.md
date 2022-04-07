# DB 설정 정보

```
 # Mysql
datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/boonmoja?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true
        username: {username}
        password: {password}
        hikari:
            pool-name: jpa-hikari-pool
            maximum-pool-size: 5
            jdbc-url: ${spring.datasource.url}
            username: ${spring.datasource.username}
            password: ${spring.datasource.password}
            driver-class-name: ${spring.datasource.driver-class-name}
            data-source-properties:
                rewriteBatchedStatements: true
# MongoDB
    second-datasource:
        mongodb:
            authentication-database: admin
            username: {username}
            password: {password}
            database: boonmoja
            port: 27017
            host: localhost
        servlet:
            multipart:
                max-file-size: 5MB
                max-request-size: 5MB
                enabled: true

```
