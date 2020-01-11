# Autolog examples
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

Examples of usage of [Autolog library](https://github.com/maximevw/autolog).

## Getting started
### Prerequisites

Running Autolog examples requires **JDK 11 or greater**.

### Installing
#### autolog-spring-example

This example shows how to use Autolog in a Spring Boot application using Spring AOP for logging automation.

To run this example, execute the following command:
```
mvn clean package spring-boot:run -P<profile>
```
If you are using IntelliJ, use one of the run configurations `Run Spring Boot example with profile <profile>`.

The available profiles are the following:
- `default` (active if no specific profile is mentioned): example using standard system output, starts on port 8080.
- `slf4j`: example using Slf4j with Logback Classic implementation, starts on port 8081.
- `log4j2`: example using Log4j2, starts on port 8082.

Once the application is started, call the API by executing the following command:
```
curl "http://localhost:<port>/example/hello?name=<value>"
```

#### autolog-no-automation-example

This example shows how to use Autolog without logging automation but using direct calls to public methods provided by
the module `autolog-core`.

**Note:** Using Autolog this way is not recommended since it is not the purpose of this library and it will make your
code less readable.

To run this example, execute the following command:
```
mvn clean compile exec:java -Dexec.mainClass="com.github.maximevw.autolog.examples.SimpleApplication"
```

If you are using IntelliJ, use the run configuration `Run no automation example`.

You can also download the bundled `autolog-core` jar [here](https://github.com/maximevw/autolog/releases/download/v1.0.0/autolog-core-1.0.0-bundle.jar)
, then copy it into the directory where the example classes are compiled and run this command:
```
java -classpath classes:autolog-core-1.0.0-bundle.jar com.github.maximevw.autolog.examples.SimpleApplication
```

## License

Autolog is distributed under [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt).
