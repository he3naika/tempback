Castle Water
===

Documentation to read
---------------------

In order to build the project, please use next command:
 
  * `mvn clean package`
   
In order to deploy project, please use next command:
  
  * `java -jar myaccount-rest/myaccount-rest-impl/target/app.jar`
  
##Docker commands:
1. Build image `docker build -t <IMAGE NAME> .`
2. Run container `docker run -p 8080:8080 <IMAGE NAME>`

##Main docker commands:
* `dockre --help` -- List docker supported commands
* `docker images` -- List images
* `docker build` -- Build an image from a Dockerfile
* `docker ps -a` -- Show all containers (default shows just running)
* `docker stop <CONTAINER ID>` -- Stop one or more running containers
* `docker rm <CONTAINER ID>` -- Remove one or more containers
* `docker logs <CONTAINER ID>` -- Fetch the logs of a container

Point your web browser at http://localhost:8080/api/state to display the state page.

![demo](https://image.prntscr.com/image/8BKiSN7FTie0MHP5-cgEiA.png)

In order to open description of API, please, use the next link http://localhost:8080/api/swagger-ui.html

Test #1
Test #2
