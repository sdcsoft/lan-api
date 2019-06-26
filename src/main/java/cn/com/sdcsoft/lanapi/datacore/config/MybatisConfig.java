package cn.com.sdcsoft.lanapi.datacore.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "cn.com.sdcsoft.lanapi.datacore.mapper",sqlSessionFactoryRef = "dataCoreSqlSessionFactory")
public class MybatisConfig {
    @Bean(name = "dataCoreDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.datacore")
    @Primary
    public DataSource dataCoreDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataCoreSqlSessionFactory")
    @Primary
    public SqlSessionFactory dataCoreSqlSessionFactory(@Qualifier("dataCoreDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //读取mybatis小配置文件
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "dataCoreTransactionManager")
    @Primary
    public DataSourceTransactionManager dataCoreTransactionManager(@Qualifier("dataCoreDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dataCoreSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dataCoreSqlSessionTemplate(@Qualifier("dataCoreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
