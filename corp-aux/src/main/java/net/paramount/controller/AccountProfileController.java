package net.paramount.controller;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.paramount.auth.component.JwtTokenProvider;
import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.service.UserAccountService;
import net.paramount.domain.model.ServerResponse;
import net.paramount.domain.model.ServerResponseCode;
import net.paramount.framework.controller.BaseController;
import net.paramount.framework.entity.auth.AuthenticationDetails;

/**
 * @author ducbq
 */
@RestController
@Controller
@RequestMapping(value = "/protected/accountProfile/")
public class AccountProfileController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8922161788640253600L;

	@Inject
	private JwtTokenProvider tokenProvider;

	//@Inject 
	//private AuthorizationService authorizationService;
	
	@Inject
	private UserAccountService userAccountService;

	@RequestMapping(
			value = "/confirm/{token}", 
			method = RequestMethod.GET)
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public ModelAndView confirm(HttpServletRequest request, HttpServletResponse response, @PathVariable("token") String token){
		AuthenticationDetails userDetails = null;
		UserAccount confirnUserAccount = null;
		try {
			userDetails = tokenProvider.getUserDetailsFromJWT(token);
			if (userDetails != null) {
				confirnUserAccount = userAccountService.get(userDetails.getSsoId());
			}

			remoteLogin(request, confirnUserAccount);

			//System.out.println(confirnUserAccount);
      // Getting servlet request URL
      String url = request.getRequestURL().toString();

      // Getting servlet request query string.
      String queryString = request.getQueryString();

      // Getting request information without the hostname.
      String uri = request.getRequestURI();

      // Below we extract information about the request object path information.
      String scheme = request.getScheme();
      String serverName = request.getServerName();
      int portNumber = request.getServerPort();
      String contextPath = request.getContextPath();
      String servletPath = request.getServletPath();
      String pathInfo = request.getPathInfo();
      String query = request.getQueryString();

      System.out.println("Url: " + url);
      System.out.println("Uri: " + uri);
      System.out.println("Scheme: " + scheme);
      System.out.println("Server Name: " + serverName);
      System.out.println("Port: " + portNumber);
      System.out.println("Context Path: " + contextPath);
      System.out.println("Servlet Path: " + servletPath);
      System.out.println("Path Info: " + pathInfo);
      System.out.println("Query: " + query);
      
			//Decode password before login
			//doAutoLogin(request, confirnUserAccount.getSsoId(), confirnUserAccount.getPassword());
		} catch (Exception e) {
			log.error(e);
		}

		/*try {
			response.sendRedirect("/index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}*/
		return new ModelAndView ("redirect:/index.jsf");//getServerResponse(ServerResponseCode.SUCCESS, false);
	}

	/*@RequestMapping("confirm")	
	public ServerResponse schedule(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.schedule()");

		return getServerResponse(ServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
	}*/

	@RequestMapping(value="unsubscribe")
	public void unschedule(HttpServletRequest request, HttpServletResponse response, @RequestParam("jobName") String jobName) {
		System.out.println("JobController.unschedule()");
		try {
			AuthenticationDetails userDetails = tokenProvider.getUserDetailsFromJWT(jobName);
			System.out.println(userDetails);
			doAutoLogin(request, "administrator", "admin@administrator");
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
