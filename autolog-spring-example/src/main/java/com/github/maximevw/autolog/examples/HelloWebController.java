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

package com.github.maximevw.autolog.examples;

import com.github.maximevw.autolog.core.annotations.AutoLogMethodInOut;
import com.github.maximevw.autolog.core.annotations.AutoLogPerformance;
import com.github.maximevw.autolog.examples.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AutoLogMethodInOut
@AutoLogPerformance
public class HelloWebController {

	@Autowired
	private HelloService helloService;

	@GetMapping("/hello")
	public String sayHello(@RequestParam final String name) {
		return helloService.sayHello(name);
	}

}
