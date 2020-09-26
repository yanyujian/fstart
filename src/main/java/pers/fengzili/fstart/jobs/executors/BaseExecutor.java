package pers.fengzili.fstart.jobs.executors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import pers.fengzili.fstart.jobs.JobInfor;

/**
 * created by fengzili at	2020-09-26 17:57:35
 */
public abstract class BaseExecutor implements Job {
	
	public String getCurrentTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	protected JobInfor jobInfor;
	
	public JobInfor getJobInfor() {
		return jobInfor;
	}

	public BaseExecutor(JobInfor jobInfor) {
		super();
		this.jobInfor=jobInfor;
	}
	
	public abstract void execute(JobExecutionContext context) throws JobExecutionException;
}
