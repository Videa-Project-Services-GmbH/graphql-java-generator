# GraphQL Java Generator

This project is about generating Java classes and clients from GraphQL Schemas using the approach 'Schema First'. 

For all defined GraphQL types being Scalars, Enums, Interfaces, Inputs, Types, 
Query and Mutation, the corresponding Java classes are generated.

The project uses Open Source 3rd Party projects to fulfill its goals. 

# CLI Usage
Command from command line:
```
java -jar services.videa.graphql.java.GqlJavaGenerator SCHEMA OUT_FOLDER PACKAGE
```
Example:
```
java -jar services.videa.graphql.java.GqlJavaGenerator schema.gql src/main/generated services.videa.graphql.java
```

# Build Management
## Perform a Release Deployment to OSSRH
```
gpg --keyserver hkp://pool.sks-keyservers.net --send-keys 2B3C7FC9D2D190F682A3EA90B74BC126F4A8760C

mvn release:clean release:prepare
mvn release:perform
```
# Maven Usage
## Maven Dependency
Central Repository: [Nexus Repository Manager](https://oss.sonatype.org/#nexus-search;quick~videa-services)
```
<dependency>
  <groupId>services.videa</groupId>
  <artifactId>graphql-java-generator</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```
## Maven Plugin
Comming soon: graphql-java-maven-plugin

# Release Notes
