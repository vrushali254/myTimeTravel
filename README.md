# myTimeTravel

IDE: IntelliJ 
JavaJDK: 20 
SpringBoot: 3+
Build Tool: Maven
DB: SQLite

More SD notes:
https://whimsical.com/rainbow-rest-api-copy-3mPR5HnUUPSs3LLUbNdKa6

There is swagger integration, so you can test all the APIs using:

http://localhost:8080/swagger-ui/index.html#

[The app is running on port 8080]


Part1:
1. Integrating SQLite: Added the JDBC dependencies and Hibernate Dialect. 
2. A DatabaseConfig was created and application.properties file was modified.

Part2:
1. Used versioning to maintain the state of records. 
2. So each time v1 API is used, if the recordId exists, a new version of the recordId will be created. 
3. Diff calculation for prevRecordStateData and currentRecordStateData is also being handled when creating a new version for the recordId.
4. Each record object is timeStamped -> timeOfCreation and timeOfModification.
5. The goal of the v2 APIs is to provide the ability to partially persistent records and to be able to modify/update an existing recordId/policy. 
6. All the different states for a recordId is being maintained using a recordHistory table. [pk = recordId]

Assumption: The recordId and versionNum must exist, else we throw an error.
Assumption: recordId is an Integer, versionNum is also an Integer [both data types can be easily modified]


