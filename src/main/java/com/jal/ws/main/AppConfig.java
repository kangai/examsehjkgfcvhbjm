package com.jal.ws.main;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class AppConfig implements Config {

    @Resource
    private Environment env;

    DataSource realDataSource(){
        // application.propertiesにあるDB設定キーから値を取得し設定します。
        // コメントを修正
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));

        // TransactionAwareDataSourceProxyでラッピングしないとDoma側でコネクションがおかしくなる
        return new TransactionAwareDataSourceProxy(
               new Log4jdbcProxyDataSource(dataSource));
    }

    @Bean
    SqlFileRepository sqlFileRepository(){
        return new NoCacheSqlFileRepository();
    }

    @Override
    public DataSource getDataSource() {
        return this.realDataSource();
    }

    @Override
    public Dialect getDialect() {
        return new H2Dialect();
    }
}