package com.vicras.codeanalyzerserver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.catalina.Context;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.unit.DataSize;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.util.TimeZone;
import java.util.concurrent.Executor;

import static com.vicras.codeanalyzerserver.constant.TimeConstants.MOSCOW_TIMEZONE;
import static java.util.TimeZone.getTimeZone;

@SpringBootApplication
public class CodeAnalyzerServerApplication {

    @Value("${jdbc.database.url}")
    private String url;
    @Value("${jdbc.database.username}")
    private String username;
    @Value("${jdbc.database.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(CodeAnalyzerServerApplication.class, args);
    }

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setSchema("code-analyzer");
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setMaximumPoolSize(3);
        config.setIdleTimeout(10000);
        config.setConnectionTimeout(20000);
        return new HikariDataSource(config);
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        final DataSize maxFileSize = DataSize.ofKilobytes(100);
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxFileSize);
        return factory.createMultipartConfig();
    }

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(getTimeZone(MOSCOW_TIMEZONE));
    }

    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return tomcatServletWebServerFactory -> tomcatServletWebServerFactory.addContextCustomizers(context -> {
            Rfc6265CookieProcessor processor=new Rfc6265CookieProcessor();
            processor.setSameSiteCookies("strict");
            context.setCookieProcessor(processor);
        });
    }
}
