/**
 * 
 */
package net.paramount.listener;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import net.paramount.dmx.postconstruct.GlobalDataServiceHelper;
import net.paramount.dmx.repository.GlobaRepositoryManagement;
import net.paramount.framework.component.ComponentBase;
import net.paramount.framework.concurrent.ExecutorServiceHelper;
import net.paramount.framework.model.ExecutionContext;

/**
 * @author ducbq
 *
 */
@Component
public class GlobalEventManagement extends ComponentBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -829018786197754366L;

	@Inject
	private ExecutorServiceHelper executorServiceHelper;

	@Inject
	private GlobaRepositoryManagement globaRepositoryManagement;

	@Inject
	private GlobalDataServiceHelper globalDataServiceHelper;

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReadyEventListener() {
		globaRepositoryManagement.initiateMasterData();
		try {
			onInit();
		} catch (InterruptedException e) {
			log.error(e);
		}
		log.info("Leave onApplicationReadyEventListener");
	}

	protected void onInit() throws InterruptedException {
		globalDataServiceHelper.initiateGlobalData();
		//GlobalConstants.KEY_CONTEXT_CLASS
		/*ExecutionContext executionContext = ExecutionContext.builder()
				
				.build();
		executorServiceHelper.startThread(executionContext);*/
	}
}
