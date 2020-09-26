package pers.fengzili.fstart.jobs;

/**
 * created by fengzili at	2020-09-26 17:57:24
 */
public enum JobType {

	Notify(1),
	ExecuteProgram(2);

	private int value;

	private JobType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
