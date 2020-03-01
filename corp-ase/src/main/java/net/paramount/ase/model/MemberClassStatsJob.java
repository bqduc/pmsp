package net.paramount.ase.model;

import javax.inject.Inject;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import net.paramount.ase.base.JobSchedulerBase;
import net.paramount.ase.exceptions.JobScheduleException;
import net.paramount.ase.service.MemberClassService;
import net.paramount.framework.model.ExecutionContext;

@Component
@DisallowConcurrentExecution
public class MemberClassStatsJob extends JobSchedulerBase {
	private static final long serialVersionUID = 2646480829986538668L;

	@Inject
	private MemberClassService memberClassService;

	@Override
	protected ExecutionContext doExecute(JobExecutionContext context) throws JobScheduleException {
		String stageTitle = this.getClass().getSimpleName();
		ExecutionContext executionContext = super.buildScheduleExecutionContext(stageTitle, SCHEDULER_STAGE_ENTER);
		memberClassService.classStats();
		return executionContext.set(stageTitle, SCHEDULER_STAGE_LEAVE);
	}
}
