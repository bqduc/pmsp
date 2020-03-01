/**
 * 
 */
package net.paramount.ase.base;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import net.paramount.ase.exceptions.JobScheduleException;
import net.paramount.framework.component.CompCore;
import net.paramount.framework.model.ExecutionContext;

/**
 * @author ducbq
 *
 */
@Component
@DisallowConcurrentExecution
public class JobSchedulerBase extends CompCore implements Job {
	private static final long serialVersionUID = -2139639894732663935L;

	protected static final String SCHEDULER_STAGE_ENTER = "Enter";
	protected static final String SCHEDULER_STAGE_LEAVE = "Leave";

	public void execute(JobExecutionContext context) throws JobExecutionException {
		ExecutionContext executionContext = null;
		log.info("Job ** {} ** starting @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
    try {
    	executionContext = doExecute(context);
    	log.info(executionContext.toString());
		} catch (JobScheduleException e) {
			log.error(e);
		}
    log.info("Job ** {} ** completed.  Next job scheduled @ {}", context.getJobDetail().getKey().getName(), context.getNextFireTime());
	}

	protected ExecutionContext doExecute(JobExecutionContext context) throws JobScheduleException {
		throw new JobScheduleException("Not implemented yet!");
	}

	protected ExecutionContext buildScheduleExecutionContext(String stageTitle, String stage) {
		ExecutionContext executionContext = ExecutionContext.builder().build();
		executionContext.set(stageTitle, stage);
		return executionContext;
	}
}
