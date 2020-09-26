package pers.fengzili.fstart.jobs;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pers.fengzili.fstart.common.ExcelReaderUtil;

/**
 * 任务分发管理
 * 
 * @author fengzili
 * 
 */
@Component
public class TaskManager {

	private String jobPath;

	private List<JobInfor> jobs;

	public String getJobPath() {
		return jobPath;
	}

	@Value("${jobpath}")
	public void setJobPath(String value) {
		this.jobPath = value;
	}

	public List<JobInfor> getJobs() {
		try (FileInputStream readerStream = new FileInputStream(this.getJobPath());) {
			return ExcelReaderUtil.readExcelToBean(readerStream, JobInfor.class);
		} catch (Exception e) {
			return new ArrayList<JobInfor>();
		}
	}
}
