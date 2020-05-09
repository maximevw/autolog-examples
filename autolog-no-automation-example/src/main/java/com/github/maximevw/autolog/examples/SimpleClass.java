/*-
 * #%L
 * Autolog without automation example
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

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleClass {

	private static final Logger LOGGER = LogManager.getLogger(SimpleClass.class);

	public void doLoopAndSum(final int iterations) {
		NoAutomationLoggerManager.runWithLogs(
			// Name of the method to log, see implementation of NoAutomationLoggerManager.runWithLogs.
			"doLoopAndSum",
			// Code to execute: the implementation of the method doLoopAndSum(int).
			() -> {
				int currentSum = 0;
				for (int i = 1; i <= iterations; i++) {
					LOGGER.debug("=> iteration: " + i);
					currentSum = sum(currentSum, i);
				}
				LOGGER.debug("=> sum: " + currentSum);
			},
			// List of method arguments to log, see implementation of NoAutomationLoggerManager.runWithLogs.
			Pair.of("iterations", iterations));
	}

	private int sum(final int initValue, final int addedValue) {
		return NoAutomationLoggerManager.supplyWithLogs(
			// Name of the method to log, see implementation of NoAutomationLoggerManager.supplyWithLogs.
			"sum",
			// Code to execute: the implementation of the method sum(int, int).
			() -> initValue + addedValue,
			// List of method arguments to log, see implementation of NoAutomationLoggerManager.supplyWithLogs.
			Pair.of("initValue", initValue), Pair.of("addedValue", addedValue));
	}

}
