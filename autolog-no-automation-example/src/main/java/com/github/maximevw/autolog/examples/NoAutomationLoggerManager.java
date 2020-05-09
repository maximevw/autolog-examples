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

import com.github.maximevw.autolog.core.configuration.MethodInputLoggingConfiguration;
import com.github.maximevw.autolog.core.configuration.MethodOutputLoggingConfiguration;
import com.github.maximevw.autolog.core.configuration.MethodPerformanceLoggingConfiguration;
import com.github.maximevw.autolog.core.logger.LoggerInterface;
import com.github.maximevw.autolog.core.logger.LoggerManager;
import com.github.maximevw.autolog.core.logger.MethodCallLogger;
import com.github.maximevw.autolog.core.logger.MethodPerformanceLogger;
import com.github.maximevw.autolog.core.logger.performance.PerformanceTimer;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Supplier;

public final class NoAutomationLoggerManager {

	private static LoggerManager loggerManager;

	private static MethodPerformanceLoggingConfiguration performanceLoggingConfiguration;
	private static MethodPerformanceLogger methodPerformanceLogger;

	private static MethodInputLoggingConfiguration inputLoggingConfiguration;
	private static MethodOutputLoggingConfiguration outputLoggingConfiguration;
	private static MethodCallLogger methodCallLogger;

	/**
	 * Initializes the {@link LoggerManager} and the Autolog configuration used in this example.
	 *
	 * @param loggerInterface The implementation of {@link LoggerInterface} to register in the LoggerManager.
	 */
	public static void init(@NonNull LoggerInterface loggerInterface) {
		if (loggerManager == null) {
			loggerManager = new LoggerManager();
			loggerManager.register(loggerInterface);
		}

		methodCallLogger = new MethodCallLogger(loggerManager);
		methodPerformanceLogger = new MethodPerformanceLogger(loggerManager);

		inputLoggingConfiguration = MethodInputLoggingConfiguration.builder().build();
		outputLoggingConfiguration = MethodOutputLoggingConfiguration.builder().build();
		performanceLoggingConfiguration = MethodPerformanceLoggingConfiguration.builder().build();
	}

	/**
	 * Runs a {@link Runnable} with logging of the input arguments and of the performance information.
	 *
	 * @param methodName	The name of the run method.
	 * @param runnable		The runnable.
	 * @param args			The list of method arguments to log.
	 */
	@SafeVarargs
	public static void runWithLogs(final String methodName, final Runnable runnable,
								   final Pair<String, Object>... args) {
		// Log method input.
		methodCallLogger.logMethodInput(inputLoggingConfiguration, methodName, List.of(args));

		// Start the performance timer.
		final PerformanceTimer timer = methodPerformanceLogger.start(performanceLoggingConfiguration, methodName);

		// Run method.
		runnable.run();

		// Log exiting of the method.
		methodCallLogger.logMethodOutput(outputLoggingConfiguration, methodName);

		// Stop the performance timer and log performance information.
		methodPerformanceLogger.stopAndLog(performanceLoggingConfiguration, timer);
	}

	/**
	 * Executes a {@link Supplier} with logging of the input arguments, of the output and of the performance
	 * information.
	 *
	 * @param methodName	The name of the run method.
	 * @param supplier		The supplier.
	 * @param args			The list of method arguments to log.
	 * @return The result of the executed {@link Supplier}.
	 */
	@SafeVarargs
	public static <T> T supplyWithLogs(final String methodName, final Supplier<T> supplier,
									   final Pair<String, Object>... args) {
		// Log method input.
		methodCallLogger.logMethodInput(inputLoggingConfiguration, methodName, List.of(args));

		// Start the performance timer.
		final PerformanceTimer timer = methodPerformanceLogger.start(performanceLoggingConfiguration, methodName);

		// Execute method and keep the result to return it.
		T result = supplier.get();

		// Log method output.
		methodCallLogger.logMethodOutput(outputLoggingConfiguration, methodName, result);

		// Stop the performance timer and log performance information.
		methodPerformanceLogger.stopAndLog(performanceLoggingConfiguration, timer);

		// Return the result of the executed method.
		return result;
	}

}
