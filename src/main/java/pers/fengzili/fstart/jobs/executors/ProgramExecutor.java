package pers.fengzili.fstart.jobs.executors;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import pers.fengzili.fstart.jobs.JobInfor;
import pers.fengzili.fstart.jobs.JobType;

/**
 * created by fengzili at	2020-09-26 17:58:04
 */
public class ProgramExecutor extends BaseExecutor {

	public ProgramExecutor() {
		super(null);
	}

	public ProgramExecutor(JobInfor jobInfor) {
		super(jobInfor);
		if (jobInfor.getType() != JobType.ExecuteProgram.getValue()) {
			throw new RuntimeException("任务类型错误");
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobInfor jobInfor = this.jobInfor;
		if (jobInfor == null) {
			jobInfor = (JobInfor) context.getMergedJobDataMap().get("jobInfor");
		}
		System.out.println(jobInfor.getName()+this.hashCode());
		System.out.print(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("执行" + jobInfor.toString());
		try {
			Runtime.getRuntime().exec(jobInfor.getProgramPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(jobInfor.toString());
		}
	}

}
