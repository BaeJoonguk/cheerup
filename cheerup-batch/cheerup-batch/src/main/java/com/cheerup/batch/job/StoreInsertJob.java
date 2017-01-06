package com.cheerup.batch.job;

import java.io.IOException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cheerup.batch.service.SolutionMatchService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StoreInsertJob extends QuartzJobBean {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		SolutionMatchService storeInsertService = (SolutionMatchService) ctx.getMergedJobDataMap().get("solutionMatchService");

		try {
			storeInsertService.doSomething();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}