# Spring Cloud Data Flow Tutorial


# Deployment

Start dataflow shell
``` 
docker exec -it dataflow-server java -jar shell.jar
```

In dataflow shell register the apps
``` 
dataflow:> app register --name source-app --type source --uri maven://com.javainuse:source:jar:0.0.2-SNAPSHOT
dataflow:> app register --name processor-app --type processor --uri maven://com.javainuse:processor:jar:0.0.1-SNAPSHOT
dataflow:> app register --name sink-app --type sink --uri maven://com.javainuse:sink:jar:0.0.1-SNAPSHOT
```
Create a data stream
``` 
dataflow:> stream create --name log-data --definition 'source-app | processor-app | sink-app'
```
Deploy stream
``` 
dataflow:> stream deploy --name log-data
```
## Resources
- https://www.javainuse.com/spring/cloud-data-flow
- https://www.e4developer.com/2018/02/18/getting-started-with-spring-cloud-data-flow/
