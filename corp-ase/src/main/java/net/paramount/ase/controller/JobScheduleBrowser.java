package net.paramount.ase.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.paramount.ase.model.JobSchedule;
import net.paramount.ase.model.ServerResponse;
import net.paramount.ase.model.ServerResponseCode;
import net.paramount.ase.scheduler.CronJob;
import net.paramount.ase.scheduler.SimpleJob;
import net.paramount.ase.service.JobService;
import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.domain.model.Filter;

/**
 * @author ducbq
 */
@Named(value = "jobScheduleBrowser")
@ViewScoped
public class JobScheduleBrowser  implements Serializable {
	private static final long serialVersionUID = -7599883929786783259L;

	private String touchJobName;

	@Inject
	@Lazy
	private JobService businessService;

	private List<JobSchedule> selectedObjects;
	private List<JobSchedule> businessObjects;
	private Filter<JobSchedule> bizFilter = new Filter<>(new JobSchedule());
	private List<JobSchedule> filteredObjects;

	private String instantSearch;
	Long id;

	Filter<JobSchedule> filter = new Filter<>(new JobSchedule());

	List<JobSchedule> filteredValue;

	@PostConstruct
	public void initDataModel() {
		try {
			System.out.println("==>Come to authority browser!");
			this.businessObjects = this.businessService.getAllScheduledJobs();
			//this.businessObjects = businessService.getObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		filter = new Filter<JobSchedule>(new JobSchedule());
	}

	public List<String> completeModel(String query) {
		List<String> result = ListUtility.createDataList();// carService.getModels(query);
		return result;
	}

	public void search(String parameter) {
		System.out.println("Searching parameter: " + parameter);
	}

	public void delete() {
		if (CommonUtility.isNotEmpty(this.selectedObjects)) {
			for (JobSchedule removalItem : this.selectedObjects) {
				System.out.println("#" + removalItem.getName());
				this.businessObjects.remove(removalItem);
			}
			this.selectedObjects.clear();
		}
	}

	public List<JobSchedule> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<JobSchedule> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public Filter<JobSchedule> getFilter() {
		return filter;
	}

	public void setFilter(Filter<JobSchedule> filter) {
		this.filter = filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<JobSchedule> getBusinessObjects() {
		return businessObjects;
	}

	public void setBusinessObjects(List<JobSchedule> businessObjects) {
		this.businessObjects = businessObjects;
	}

	public List<JobSchedule> getSelectedObjects() {
		return selectedObjects;
	}

	public void setSelectedObjects(List<JobSchedule> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	public Filter<JobSchedule> getBizFilter() {
		return bizFilter;
	}

	public void setBizFilter(Filter<JobSchedule> bizFilter) {
		this.bizFilter = bizFilter;
	}

	public List<JobSchedule> getFilteredObjects() {
		return filteredObjects;
	}

	public void setFilteredObjects(List<JobSchedule> filteredObjects) {
		this.filteredObjects = filteredObjects;
	}

	public String getInstantSearch() {
		return instantSearch;
	}

	public void setInstantSearch(String instantSearch) {
		this.instantSearch = instantSearch;
	}

	public void recordsRowSelected(AjaxBehaviorEvent e) {
		System.out.println("recordsRowSelected");
	}
	
	
	@RequestMapping("schedule")	
	public ServerResponse schedule(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.schedule()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}

		//Check if job Name is unique;
		if(!businessService.isJobWithNamePresent(jobName)){

			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				boolean status = businessService.scheduleOneTimeJob(jobName, SimpleJob.class, jobScheduleTime);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, businessService.getAllJobs());
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}
				
			}else{
				//Cron Trigger
				boolean status = businessService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, businessService.getAllJobs());
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}				
			}
		}else{
			return getServerResponse(ServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
		}
	}

	@RequestMapping("unschedule")
	public void unschedule(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.unschedule()");
		businessService.unScheduleJob(jobName);
	}

	@RequestMapping("delete")
	public ServerResponse delete(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.delete()");		

		if(businessService.isJobWithNamePresent(jobName)){
			boolean isJobRunning = businessService.isJobRunning(jobName);

			if(!isJobRunning){
				boolean status = businessService.deleteJob(jobName);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, true);
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}
			}else{
				return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}
		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("pause")
	public ServerResponse pause(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.pause()");

		if(businessService.isJobWithNamePresent(jobName)){

			boolean isJobRunning = businessService.isJobRunning(jobName);

			if(!isJobRunning){
				boolean status = businessService.pauseJob(jobName);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, true);
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}			
			}else{
				return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}		
	}

	@RequestMapping("resume")
	public ServerResponse resume(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.resume()");

		if(businessService.isJobWithNamePresent(jobName)){
			String jobState = businessService.getJobState(jobName);

			if(jobState.equals("PAUSED")){
				System.out.println("Job current state is PAUSED, Resuming job...");
				boolean status = businessService.resumeJob(jobName);

				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, true);
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}
			}else{
				return getServerResponse(ServerResponseCode.JOB_NOT_IN_PAUSED_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("update")
	public ServerResponse updateJob(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.updateJob()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}

		//Edit Job
		if(businessService.isJobWithNamePresent(jobName)){
			
			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				boolean status = businessService.updateOneTimeJob(jobName, jobScheduleTime);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, businessService.getAllJobs());
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}
				
			}else{
				//Cron Trigger
				boolean status = businessService.updateCronJob(jobName, jobScheduleTime, cronExpression);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, businessService.getAllJobs());
				}else{
					return getServerResponse(ServerResponseCode.ERROR, false);
				}				
			}
			
			
		}else{
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("jobs")
	public ServerResponse getAllJobs(){
		System.out.println("JobController.getAllJobs()");

		List<Map<String, Object>> list = businessService.getAllJobs();
		return getServerResponse(ServerResponseCode.SUCCESS, list);
	}

	@RequestMapping("checkJobName")
	public ServerResponse checkJobName(@RequestParam("jobName") String jobName){
		System.out.println("JobController.checkJobName()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}
		
		boolean status = businessService.isJobWithNamePresent(jobName);
		return getServerResponse(ServerResponseCode.SUCCESS, status);
	}

	@RequestMapping("isJobRunning")
	public ServerResponse isJobRunning(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.isJobRunning()");

		boolean status = businessService.isJobRunning(jobName);
		return getServerResponse(ServerResponseCode.SUCCESS, status);
	}

	@RequestMapping("jobState")
	public ServerResponse getJobState(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.getJobState()");

		String jobState = businessService.getJobState(jobName);
		return getServerResponse(ServerResponseCode.SUCCESS, jobState);
	}

	@RequestMapping("stop")
	public ServerResponse stopJob(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.stopJob()");

		if(businessService.isJobWithNamePresent(jobName)){

			if(businessService.isJobRunning(jobName)){
				boolean status = businessService.stopJob(jobName);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, true);
				}else{
					//Server error
					return getServerResponse(ServerResponseCode.ERROR, false);
				}

			}else{
				//Job not in running state
				return getServerResponse(ServerResponseCode.JOB_NOT_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("start")
	public ServerResponse startJobNow(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.startJobNow()");

		if(businessService.isJobWithNamePresent(jobName)){

			if(!businessService.isJobRunning(jobName)){
				boolean status = businessService.startJobNow(jobName);

				if(status){
					//Success
					return getServerResponse(ServerResponseCode.SUCCESS, true);

				}else{
					//Server error
					return getServerResponse(ServerResponseCode.ERROR, false);
				}

			}else{
				//Job already running
				return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	public ServerResponse getServerResponse(int responseCode, Object data){
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse; 
	}
}
