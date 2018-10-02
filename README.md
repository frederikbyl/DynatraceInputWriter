
# Dynatrace Input File Writer

This project is an utility to capture the input from Dynatrace to our system.

It reads all the calls and writes them to a file.

### Prerequisites

This is based on [Spring Boot](https://projects.spring.io/spring-boot/) project.

### Compile and Run

The project has to be built with maven.

Execute the following command to compile it:

```
mvn clean install
```

After that, you can run the writer via maven:

```
mvn spring-boot:run
```

or directly from java:

```
java -jar target/dynatraceInputWriter-0.0.1.jar	