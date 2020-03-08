/**
 * 
 */
package net.paramount.dmx.postconstruct;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import net.paramount.common.CommonUtility;
import net.paramount.css.service.config.ConfigurationService;
import net.paramount.css.service.config.LanguageService;
import net.paramount.dmx.globe.DmxConstants;
import net.paramount.domain.entity.general.Language;
import net.paramount.entity.config.Configuration;
import net.paramount.framework.component.ComponentBase;
import net.paramount.global.GlobalConstants;
import net.paramount.lingual.helper.LingualHelper;

/**
 * @author ducbq
 *
 */
@Component
public class GlobalDataServiceHelper extends ComponentBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3228342136333869857L;

	@Inject
  private Environment environment;

	@Inject
	private LanguageService languageService;

	@Inject
	private LingualHelper lingualHelper;

	@Inject
	private ConfigurationService configurationService;

	public void initialize() {
		initLanguages();
		initCountries();
		initBasicConfigs();
	}

	public void initiateGlobalData() {
		initiateApplicatonProfile();
	}

	private void initBasicConfigs() {
		
	}

	private void initiateApplicatonProfile() {
		final String defaultProductiveLink = "https://paramounts.herokuapp.com";
		final String defaultDevelopmentLink = "http://localhost:8080";
		
		String[] activeProfiles = null;
		String runningProfile = null;
		if (false==this.configurationService.isExistsByName(GlobalConstants.CONFIG_APP_ACCESS_URL)) {
			activeProfiles = environment.getActiveProfiles();
			if (CommonUtility.isNotEmpty(activeProfiles)) {
				runningProfile = activeProfiles[0];
			}

			this.configurationService.save(Configuration.builder()
					.group(GlobalConstants.CONFIG_GROUP_APP)
					.name(GlobalConstants.CONFIG_APP_ACCESS_URL)
					.value((runningProfile.contains("postgres") || runningProfile.contains("develop"))?defaultDevelopmentLink:defaultProductiveLink)
					.build());
		}
	}

	private void initLanguages() {
		log.info("Enter languagues intialize");
		try {
			if (1 > this.languageService.count("code", DmxConstants.LANGUAGE_CODE_VIETNAM)) {
				this.languageService.saveOrUpdate(Language.builder().code(DmxConstants.LANGUAGE_CODE_VIETNAM).name("Vietnamese").build());
			}

			if (1 > this.languageService.count("code", DmxConstants.LANGUAGE_CODE_ENGLISH)) {
				this.languageService.saveOrUpdate(Language.builder().code(DmxConstants.LANGUAGE_CODE_ENGLISH).name("English").build());
			}
		} catch (Exception e) {
			log.error(e);
		}
		log.info("Leave languagues intialize");
	}

	private void initCountries() {
		log.info("Enter countries intialize");
		this.lingualHelper.initAvailableCountries();
		log.info("Leave countries intialize");
	}

}
