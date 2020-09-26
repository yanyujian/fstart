package pers.fengzili.fstart.jobs;

import org.springframework.core.annotation.Order;

public class JobInfor {

	@Order(1)
	private String name;
	@Order(2)
	private int type;
	@Order(3)
	private int enabled;
	@Order(4)
	private int executeWhenStart;
	@Order(5)
	private String cronExpression;
	@Order(6)
	private String programPath;
	@Order(7)
	private String data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getProgramPath() {
		return programPath;
	}

	public void setProgramPath(String programPath) {
		this.programPath = programPath;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getExecuteWhenStart() {
		return executeWhenStart;
	}

	public void setExecuteWhenStart(int executeWhenStart) {
		this.executeWhenStart = executeWhenStart;
	}

	@Override
	public String toString() {
		return "JobInfor [name=" + name + ", type=" + type + ", enabled=" + enabled + ", cronExpression="
				+ cronExpression + ", programPath=" + programPath + ", data=" + data + ", executeWhenStart="
				+ executeWhenStart + "]";
	}
	
}
