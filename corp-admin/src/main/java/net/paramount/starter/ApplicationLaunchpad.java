/**
 * 
 */
package net.paramount.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ducbq
 *
 */
@Slf4j
public class ApplicationLaunchpad {
	protected ApplicationLaunchpad() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication mainApp = null;
		ConfigurableApplicationContext configAppContext = null;
		try {
			mainApp = new SpringApplication(WebAminApplication.class);
			configAppContext = mainApp.run(args);
			log.info("Configurable application context: {}", configAppContext);
		} catch (Exception e) {
			if (e.getClass().getName().contains("SilentExitException")) {
				log.debug("Spring is restarting the main thread - See spring-boot-devtools");
			} else {
				log.error("Application crashed!", e);
			}
		}
	}
}
