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
