package com.aotain.baobiao.config.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.aotain.baobiao.mapper")
public class DruidDataSourceConfig implements EnvironmentAware {
	
	 private RelaxedPropertyResolver propertyResolver;
	 
	// 注册dataSource
	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource druidDataSource = new DruidDataSource();	
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));
		druidDataSource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));
		druidDataSource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
		druidDataSource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));
		druidDataSource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));
		druidDataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		druidDataSource.setFilters(propertyResolver.getProperty("filters"));
		List<Filter> filterList=null;

		List<Filter> filters = druidDataSource.getProxyFilters();
		boolean isExit = false;
		for (Filter filter:filters){
			if (filter instanceof WallFilter){
				((WallFilter) filter).setConfig(wallConfig());
				isExit = true;
			}
		}
		if(!isExit){
			filterList = new ArrayList<>();
			filterList.add(wallFilter());
			druidDataSource.setProxyFilters(filterList);
		}
		return druidDataSource;
	}
	
	@Bean
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		wallFilter.setConfig(wallConfig());
		return wallFilter;
	}

	@Bean
	public WallConfig wallConfig() {
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);// 允许一次执行多条语句
		config.setNoneBaseStatementAllow(true);// 允许非基本语句的其他语句
		return config;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		// mybatis分页
		// PageHelper pageHelper = new PageHelper();
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties props = new Properties();
		props.setProperty("helperDialect", "mysql");
		props.setProperty("reasonable", "true");
		props.setProperty("supportMethodsArguments", "true");
		props.setProperty("returnPageInfo", "check");
		props.setProperty("params", "count=countSql");
		pageInterceptor.setProperties(props); // 添加插件
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageInterceptor });
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:sqlmap/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.aotain.baobiao.model");
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate( SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public void setEnvironment(Environment environment) {
		 this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}
	
}
