package pers.fengzili.fstart.jobs.executors;

import pers.fengzili.fstart.jobs.JobInfor;
import pers.fengzili.fstart.jobs.JobType;

/**
 * created by fengzili at	2020-09-26 17:57:44
 */
public class JobExecutorFacade {

	
	public static BaseExecutor getJobExecutor(JobInfor jobInfor) throws Exception {
		if(jobInfor.getType()==JobType.Notify.getValue()) {
			return new NotifyExecutor(jobInfor);
		}
		else if (jobInfor.getType()==JobType.ExecuteProgram.getValue()) {
			return new ProgramExecutor(jobInfor);
		}
		else {
			throw new Exception("配置错误");
		}
		
	}
	
}
