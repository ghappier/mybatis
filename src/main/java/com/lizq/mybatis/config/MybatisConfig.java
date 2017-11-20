package com.lizq.mybatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;

@Configuration
@MapperScan("com.lizq.mybatis.**.mapper")
public class MybatisConfig {

	private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

	// private final String configLocation = "classpath:mybatis-config.xml";

	private final String mapperLocations = "mapper/**/*Mapper*.xml";

	private final String dialectClass = "com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect";

	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	@Primary
	public DataSource dataSource() {
		logger.info("master com.alibaba.druid.pool.DruidDataSource will be create.....");
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
		logger.info("initialization sqlSessionFactory...");
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			
			// 设置类型转换器目录,在mybatis-config里指定
			// bean.setTypeHandlersPackage(typeHandlersPackage);
			
			// 设置分页插件
			OffsetLimitInterceptor interceptor = new OffsetLimitInterceptor();
			interceptor.setDialectClass(dialectClass);
			bean.setPlugins(new Interceptor[] { interceptor });
			
			// 指定Mapper XML文件目录
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			bean.setMapperLocations(resolver.getResources(mapperLocations));
			
			// 指定mybatis-config文件位置
			// Resource fileResource = new
			// DefaultResourceLoader().getResource(configLocation);
			// if (fileResource.exists()) {
			// bean.setConfigLocation(fileResource);
			// } else {
			// logger.info(configLocation + "不存在");
			// }
			return bean.getObject();
		} catch (Exception e) {
			logger.error("创建SqlSessionFactory失败", e);
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		logger.info("initialization transactionManager...");
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
