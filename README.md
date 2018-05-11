# Visual Github

This project is to visualize data from github's user repository data

## Architecture

![Image](https://github.com/JayStevency/visual-github/blob/master/visual-github-architecture.png)


## Requirement

- Java 1.8
- node.js 8.x
- maven 3.x
- gulp


## How to do I run?

### Web-front

> `cd web-front`
> `npm install`
> `gulp run`


### Backend

#### Based spring-boot

> `cd ${directory_name}`
>> `./mvnw clean`
>> `./mvnw install`
>> `java -jar target/${directory_name}-0.0.1-SNAPSHOT.jar`

> or

>> `./mvnw spring-boot:run`


#### Order of Build


**discover**, **github-api** -> **gateway**, **sidecar**, **search**, **mapreduce**



 