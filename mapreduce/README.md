# Mapreduce

Map Reduce

## How to run?

### localhost

> `./mvnw spring-boot:run`

> or

>`./mvnw clean`

>`./mvnw install`

>`java -jar target/mapreduce-0.0.1-SNAPSHOT.jar`

### How to implement MapReduce

  **JobTracker** 
  
    > To allocate data to Map & Reduce task
      
  **JobPool**
    
    > To manage thread using ExecutorService
    > Map task -> FixedThreadPool
    > Reduce task -> SingleThreadPool
    
  **JobMap**
  
    > To count GitHub Language data frequency

  **JobReduce**
  
    > To merge Map result
  
