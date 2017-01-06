package com.cheerup.batch;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.cheerup.batch.job.StoreInsertJob;
import com.cheerup.batch.service.SolutionMatchService;

@Configuration
@ComponentScan("com.cheerup.batch")
public class QuartzConfiguration {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.db.driver}")
	String DB_DRIVER;

	@Value("${app.db.url}")
	String DB_URL;

	@Value("${app.db.username}")
	String DB_USERNAME;

	@Value("${app.db.password}")
	String DB_PASSWORD;

	@Value("${cron.store.insert}")
	String CRONT_STORE_INSERT;

	@Autowired
	SolutionMatchService solutionMatchService;

	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(StoreInsertJob.class);
		factory.setGroup("mygroup");
		factory.setName("myjob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(1000);

		Map<String, Object> jobDataAsMap = new HashMap<String, Object>();
		jobDataAsMap.put("solutionMatchService", solutionMatchService);
		stFactory.setJobDataAsMap(jobDataAsMap);

		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		stFactory.setCronExpression(CRONT_STORE_INSERT);
		return stFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}
