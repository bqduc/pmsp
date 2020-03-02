package net.paramount.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.paramount.auth.component.JwtTokenProvider;
import net.paramount.domain.model.ServerResponse;
import net.paramount.domain.model.ServerResponseCode;
import net.paramount.framework.entity.auth.AuthenticationDetails;

/**
 * @author ducbq
 */
@RestController
@Controller
@RequestMapping(value = "/protected/accountProfile/")
public class AccountProfileController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8922161788640253600L;

	@Inject
	private JwtTokenProvider tokenProvider;

	@Inject
	private AuthenticationProvider corpAuthenticationProvider;
	
	@RequestMapping("confirm")	
	public ServerResponse schedule(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.schedule()");

		return getServerResponse(ServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
	}

	@RequestMapping(value="unsubscribe")
	public void unschedule(HttpServletRequest request, HttpServletResponse response, @RequestParam("jobName") String jobName) {
		System.out.println("JobController.unschedule()");
		try {
			AuthenticationDetails userDetails = tokenProvider.getUserDetailsFromJWT(jobName);
			System.out.println(userDetails);
			doAutoLogin("administrator", "admin@administrator", request);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		try {
			response.sendRedirect("/index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //response.setHeader("Location", "/index.jsf");
    //response.setStatus(302);
		//return new ModelAndView("redirect:/");	
	}

	private void doAutoLogin(String username, String password, HttpServletRequest request) {

		try {
			// Must be called from request filtered by Spring Security, otherwise
			// SecurityContextHolder is not updated
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authentication = this.corpAuthenticationProvider.authenticate(token);
			//logger.debug("Logging in with [{}]", authentication.getPrincipal());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			//logger.error("Failure in autoLogin", e);
		}
	}	

	@RequestMapping("delete")
	public ServerResponse delete(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.delete()");		
		return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	}

	@RequestMapping("pause")
	public ServerResponse pause(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.pause()");

		return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	}

	@RequestMapping("resume")
	public ServerResponse resume(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.resume()");
		return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
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

		return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	}

	@RequestMapping("jobs")
	public ServerResponse getAllJobs(){
		System.out.println("JobController.getAllJobs()");

		return getServerResponse(ServerResponseCode.SUCCESS, false);
	}

	@RequestMapping("checkJobName")
	public ServerResponse checkJobName(@RequestParam("jobName") String jobName){
		System.out.println("JobController.checkJobName()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}
		return getServerResponse(ServerResponseCode.SUCCESS, false);
	}

	@RequestMapping("isJobRunning")
	public ServerResponse isJobRunning(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.isJobRunning()");

		return getServerResponse(ServerResponseCode.SUCCESS, false);
	}

	@RequestMapping("jobState")
	public ServerResponse getJobState(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.getJobState()");

		return getServerResponse(ServerResponseCode.SUCCESS, false);
	}

	@RequestMapping("stop")
	public ServerResponse stopJob(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.stopJob()");
		return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	}

	@RequestMapping("start")
	public ServerResponse startJobNow(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.startJobNow()");
		return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	}

	public ServerResponse getServerResponse(int responseCode, Object data){
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse; 
	}
}
