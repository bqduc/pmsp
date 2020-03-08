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

package net.paramount.starter;

import java.util.List;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import net.paramount.config.BaseConfiguration;
import net.paramount.domain.dummy.Car;
import net.paramount.mvp.util.UtilityService;

/**
 * @author ducbq
 */
@Import(value = { BaseConfiguration.class })
@SpringBootApplication
@EnableAsync
public class WebAminApplication {
	@Inject
	private UtilityService utilityService;

	@Bean
	public List<Car> getCars() {
		return utilityService.getCars();
	}
	
	public void init() {
		System.out.println("PARK!!!!");
	}
}
