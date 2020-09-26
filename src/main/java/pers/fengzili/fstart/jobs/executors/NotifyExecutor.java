package pers.fengzili.fstart.jobs.executors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import pers.fengzili.fstart.jobs.JobInfor;
import pers.fengzili.fstart.jobs.JobType;

/**
 * created by fengzili at	2020-09-26 17:57:54
 */
public class NotifyExecutor extends BaseExecutor {

	public NotifyExecutor() {
		super(null);

	}

	public NotifyExecutor(JobInfor jobInfor) throws Exception {
		super(jobInfor);
		if (this.jobInfor.getType() != JobType.Notify.getValue()) {
			throw new Exception("job类型必须是" + JobType.Notify.getValue());
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
		String message = this.getCurrentTime() + "\r\n" + jobInfor.getData();
		final JPanel panel = new JPanel();
		int result = JOptionPane.showConfirmDialog(panel, message, "FStart!", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, null);

	}

}
