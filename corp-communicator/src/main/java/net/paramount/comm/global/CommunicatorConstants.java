/**
 * 
 */
package net.paramount.comm.global;

/**
 * @author ducbq
 *
 */
public interface CommunicatorConstants {
	final static String DEFAULT_LOADING_TEMPLATE_DIRECTORY = "/emailTemplate/";
	//final static String PROP_TEMPLATE_ID = "templateId";
	final static String PROP_MAIL_OBJECT = "mailObject";
	
	final static String ContentTypes_TEXT_PLAIN = "text/plain";
	final static String ContentTypes_TEXT_HTML_UTF8 = "text/html;charset=UTF-8";
	
	final static String CTX_MIME_MESSAGE = "mailMessage";
	final static String CTX_MAIL_TEMPLATE_ID = "mailTemplateId";
	final static String CTX_MAIL_TEMPLATE_DIR = "mailTemplateDir";
	final static String CTX_USER_ACCOUNT = "userAccount";

	final static String CTX_DEFAULT_REGISTRATION_SUBJECT = "Welcome to your new account";
}
