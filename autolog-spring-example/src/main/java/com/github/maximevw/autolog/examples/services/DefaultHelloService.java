/*-
 * #%L
 * Autolog with Spring Boot example
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

package com.github.maximevw.autolog.examples.services;

import com.github.maximevw.autolog.core.annotations.AutoLogMethodInOut;
import com.github.maximevw.autolog.core.annotations.AutoLogPerformance;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@AutoLogMethodInOut
@AutoLogPerformance
@Profile("default")
public class DefaultHelloService implements HelloService {

	public String sayHello(final String name) {
		System.out.println("Going to say hello to " + name + "...");
		return "Hello " + name + "!";
	}

}
