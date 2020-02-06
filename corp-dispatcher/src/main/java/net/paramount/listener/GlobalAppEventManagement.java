/**
 * 
 */
package net.paramount.listener;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import net.paramount.dmx.repository.GlobaRepositoryManagement;
import net.paramount.framework.component.ComponentBase;

/**
 * @author ducbq
 *
 */
@Component
public class GlobalAppEventManagement extends ComponentBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -829018786197754366L;

	@Inject
	private GlobaRepositoryManagement globaRepositoryManagement;

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReadyEventListener() {
		globaRepositoryManagement.initiateMasterData();
		log.info("Leave onApplicationReadyEventListener");
	}
}
