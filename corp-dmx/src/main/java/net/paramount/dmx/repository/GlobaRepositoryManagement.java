/**
 * 
 */
package net.paramount.dmx.repository;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import net.paramount.dmx.postconstruct.GlobalDataServiceHelper;
import net.paramount.framework.component.ComponentBase;

/**
 * @author ducbq
 *
 */
@Component
public class GlobaRepositoryManagement extends ComponentBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4395890941278552748L;

	@Inject
	private GlobalDataServiceHelper globalDataServiceHelper;

	public void initMasterData() {
		log.info(String.join(LOG_ENTRY_ENTER, "GlobalDataRepository", "::", "initMasterData"));
		initInventoryItems();
		log.info(String.join(LOG_ENTRY_LEAVE, "GlobalDataRepository", "::", "initMasterData"));
	}

	protected void initInventoryItems() {
		//String logSpec = String.join(this.getClass().getSimpleName(), "::", Thread.currentThread().getStackTrace()[1].getMethodName());
		//log.info(String.join(LOG_ENTRY_ENTER, logSpec));
		//log.info(String.join(LOG_ENTRY_LEAVE, logSpec));
	}

	public void initiateMasterData() {
		log.info("Enter initiateMasterData");
		globalDataServiceHelper.initialize();
		log.info("Leave initiateMasterData");
	}
}
