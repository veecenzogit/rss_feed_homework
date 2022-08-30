
# Homework task 
## Requirements
Adding new XML feed
Viewing Saved XML feed

## Build using
**Java 8 (corretto)**;
**SpringBoot**;
**H2 database**;
**JSP templates**;

## Running application
### Run using Intellij IDEA
> If run on Intellij IDEA Annotation Processors has to be enabled for compiler to work with Lombok
> Launch application via gradle panel by executing bootRun

### Run using command line (make sure JAVA_HOME var is set)
```PROJECT_DIR./gradlew bootRun```

### Application access
```localhost:8080```

### H2 Access
>Application uses H2 in memory embedded database
>H2 is populated with sample data on app startup
>Schema can be accessed via console (access details present in application.properties)
```localhost:8080/h2-console```
