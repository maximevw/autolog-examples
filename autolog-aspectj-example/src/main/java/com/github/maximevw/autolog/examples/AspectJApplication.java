/*-
 * #%L
 * Autolog with AspectJ example
 * %%
 * Copyright (C) 2019 - 2020 Maxime WIEWIORA
 * %%
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
 * #L%
 */

package com.github.maximevw.autolog.examples;

import com.github.maximevw.autolog.aspectj.configuration.AspectJLoggerManager;
import com.github.maximevw.autolog.core.annotations.AutoLogMethodInOut;
import com.github.maximevw.autolog.core.annotations.AutoLogPerformance;
import com.github.maximevw.autolog.core.logger.LoggerManager;
import com.github.maximevw.autolog.core.logger.adapters.Log4j2Adapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AspectJApplication {

	private static final Logger LOGGER = LogManager.getLogger(AspectJApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("Instantiate the logger manager for the application...");
		final LoggerManager loggerManager = new LoggerManager();
		loggerManager.register(Log4j2Adapter.getInstance());
		AspectJLoggerManager.getInstance().init(loggerManager);

		LOGGER.info(logPersonAge("Martha", LocalDate.of(1978, 10, 20)));
		LOGGER.info(logPersonAge("Joe", LocalDate.of(1990, 1, 15)));
	}

	@AutoLogPerformance
	@AutoLogMethodInOut
	private static String logPersonAge(final String name, final LocalDate birthDate) {
		final int age = LocalDate.now().compareTo(birthDate);
		return String.format("%s is %d years old.", name, age);
	}

}
