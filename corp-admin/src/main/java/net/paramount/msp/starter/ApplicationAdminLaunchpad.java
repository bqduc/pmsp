/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.paramount.msp.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;
import net.paramount.config.BaseConfiguration;
import net.paramount.exceptions.CryptographyException;
import net.paramount.security.CryptographyAlgorithm;
import net.paramount.security.GlobalCryptogramRepository;
import net.paramount.security.base.Cryptographer;

/**
 * @author ducbq
 */
/*
@Slf4j
//@Import(value = { BaseConfiguration.class, SecurityConfig.class, MspQuartzConfig.class })
@Import(value = { BaseConfiguration.class})
@SpringBootApplication(scanBasePackages={"net.paramount"})
@EnableAsync
public class ApplicationAdminLaunchpad {

	public static void main(String[] args) {
		//testEncryption();
		ApplicationAdminLaunchpad adminStarterApplication = new ApplicationAdminLaunchpad();
		SpringApplication springBootApp = new SpringApplication(adminStarterApplication.getClass());
		ConfigurableApplicationContext configAppContext = springBootApp.run(args);
		log.info("Started application context: " + configAppContext);
	}

	private static void testEncryption() {
		final String encodedMessage = "HBzX4hQOCBp/ifGhU8N997Hq/cB/F/GY6m3bdct9jGWRDL92MzS8DhiT7FGbn4WozZb3UfxuGlQXU5DdCJv2NaWFFTE+aULl6PiConA0BVD9HztB71S+e+5z7f/1O+75mFO7CM75XDNFeXbtpuYOdWqSBXcE0Rko7F34ptLOh/OYQpfInCHhg10Va5woXjJ+ifEpbZ8d8saqzxeh0O+iert4PhTaVp6X/gT5lEuiSlovxTMKhEvDAilsPRb11QZvsRJgBe930E/s6ZveEV/R3IgSwJ8omCNzyHJniz60MRU=";
		final String message = "Không có dự án nào hoàn thành nếu không có biên bản nghiệm thu tổng thể từ khách hàng. Hầu hết các vấn đề xảy ra đã có kế hoạch giải quyết trong  quản trị rủi ro.";

		Cryptographer cryptographer = GlobalCryptogramRepository.builder().build().getCryptographer(CryptographyAlgorithm.PLAIN_TEXT);
		System.out.println("-----------------------------------------------------------------------");
		String encodedBuffer = "PScrh7xOa5eS7YKoQvLmf8e2ih8bVbpUIffYKTx7cIikA8peLu4he08VuclBkoo1";
		String encoded = null, decoded = null;
		final String secretKey = "The^' co' na`y thi` Cái gì cũng được";
		cryptographer = GlobalCryptogramRepository.builder().build().getCryptographer(CryptographyAlgorithm.PRIVATE_MEDIUM);
		//encoded = cryptographer.encode(message, secretKey);
		//System.out.println("[" + decoded + "]");

		try {
			decoded = cryptographer.decode(encodedMessage, secretKey);
		} catch (CryptographyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[" + decoded + "]");

	}
}
*/