package pers.fengzili.fstart;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.fengzili.fstart.jobs.JobInfor;
import pers.fengzili.fstart.jobs.TaskManager;
import pers.fengzili.fstart.jobs.executors.BaseExecutor;
import pers.fengzili.fstart.jobs.executors.JobExecutorFacade;


@Component
public class FJobEngine {

	@Autowired
	private TaskManager taskManager;

	public void start(String... args) throws Exception {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		int i = 0;
		for (JobInfor infor : this.taskManager.getJobs()) {
			if(infor.getEnabled()<1) {
				continue;
			}
			i++;
			BaseExecutor jobExecutor = JobExecutorFacade.getJobExecutor(infor);
			this.asyncExecuteWhenStartedIfNeeded(jobExecutor);
			if (infor.getCronExpression() != null && infor.getCronExpression().length() > 0) {
				JobDataMap dataMap = new JobDataMap();
				dataMap.put("jobInfor", infor);
				JobDetail jobDetail = JobBuilder.newJob(jobExecutor.getClass()).setJobData(dataMap)
						.withIdentity(infor.getName() + i, "group" + i).build();
				CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + i, "group" + i)
						.withSchedule(CronScheduleBuilder.cronSchedule(infor.getCronExpression())).build();
				scheduler.scheduleJob(jobDetail, trigger);
			}
		}
		scheduler.start();
	}

	protected void asyncExecuteWhenStartedIfNeeded(BaseExecutor jobExecutor) {
		if (jobExecutor == null) {
			return;
		}
		try {
			jobExecutor.execute(null);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}

	}

}
