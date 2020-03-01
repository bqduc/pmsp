/**
 * 
 */
package net.paramount.ase.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author ducbq
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSchedule implements Serializable {
	private static final long serialVersionUID = 5644809228005189498L;

	private String name;
	private String group;
	private Date scheduleTime;
	private Date lastFiredTime;
	private Date nextFireTime;
	private String status;
	private String lastTriggeredBy;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Date getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public Date getLastFiredTime() {
		return lastFiredTime;
	}
	public void setLastFiredTime(Date lastFiredTime) {
		this.lastFiredTime = lastFiredTime;
	}
	public Date getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastTriggeredBy() {
		return lastTriggeredBy;
	}
	public void setLastTriggeredBy(String lastTriggeredBy) {
		this.lastTriggeredBy = lastTriggeredBy;
	}
}