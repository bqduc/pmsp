package net.paramount.comm.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Builder;

@Builder
public class MailMessage {
	private String mailFrom;

	private String[] mailTo;

	private String[] mailCc;

	private String[] mailBcc;

	private String mailSubject;

	private String mailContent;

	@Builder.Default
	private String contentType = "text/html;charset=UTF-8";//"text/plain";//;

	private List<EmailAttachment> attachments;

	private Map<String, Object> model;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String[] getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String[] mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String[] getMailCc() {
		return mailCc;
	}

	public void setMailCc(String[] mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return mailSubject;
	}

	public void setSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String[] getMailTo() {
		return mailTo;
	}

	public void setMailTo(String[] mailTo) {
		this.mailTo = mailTo;
	}

	public Date getMailSendDate() {
		return new Date();
	}

	public String getMessageBody() {
		return mailContent;
	}

	public void setMessageBody(String mailContent) {
		this.mailContent = mailContent;
	}

	public List<EmailAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<EmailAttachment> attachments) {
		this.attachments = attachments;
	}

	public void addAttachments(List<EmailAttachment> attachments) {
		if (null==this.attachments) {
			this.attachments = attachments;
		}else {
			this.attachments.addAll(attachments);
		}
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
