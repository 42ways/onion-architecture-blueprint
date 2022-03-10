# Onion Architecture Blueprint

This project is a blueprint for the so called "Onion Architecture", which is a very popular architecture pattern for domain driven design (DDD).

The basic ideas were published as a [series of Blog Posts](https://jeffreypalermo.com/2008/07/the-onion-architecture-part-1/) by
[Jeffrey Palermo](https://jeffreypalermo.com/).

This blueprint implementation of an onion architecture started with some experiments in Java Software Architecture Patterns, esp. to get a feeling
for advantages and disadvantages of Onion Architecture and Clean Architecture principles.

Therefore, this project has as few as possible dependencies.

To make the code dependencies explicit, the project is split up into several modules with restricted access between the modules.
Real live projects would probably split these modules into different repositories.

The dependencies between the modules are:

![Onion Architecture Module Dependencies](modules.png)

## Usage
The module `main` includes some demo programs that can be started by running
the main methods from within IntelliJ or directly on the command line.

After compilation of the project you can use e.g.
```java -cp out/production/core:out/production/application:out/production/infrastructure:out/production/main:lib/* de.fourtytwoways.onion.PersonDemo```
or
```java -cp out/production/core:out/production/application:out/production/infrastructure:out/production/main:lib/* de.fourtytwoways.onion.EnumDemo```
to run the demo/test programs.

## Dependencies and setup

The project is built with the IntelliJ IDE, the repository contains the configuration files in the folder `.idea`.

The dependencies are

* Lombok 1.18.22 (https://projectlombok.org/)
* Google Guava 31.1-JRE (https://mvnrepository.com/artifact/com.google.guava/guava/31.1-jre)
* JUnit Jupiter 5.8.2 (https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.8.2)
* Hibernate 5.4.11 (https://hibernate.org/) (only for module infrastructure)
* H2 1.4.200 (https://mvnrepository.com/artifact/com.h2database/h2/1.4.200) (only for module infrastructure)

