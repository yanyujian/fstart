package pers.fengzili.fstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * created by fengzili at	2020-09-26 17:59:14
 */
@Configuration
public class Config {
	@Value("${jobpath}")
	private String jobPath;
	
	@Value("${openJobPath:cmd /c start ${jobpath}}")
	private String openJobPath;
	
	
	public String getOpenJobPath() {
		return openJobPath;
	}

	public String getJobPath() {
		return jobPath;
	}

}
