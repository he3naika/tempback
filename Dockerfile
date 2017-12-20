#Clone docker from docker hub image where Java 8 is installed
FROM java:8

#Execute next commands on new docker image
RUN mkdir /application
COPY myaccount-rest/myaccount-rest-impl/target/app.jar /application/app.jar
CMD ["java","-jar","/application/app.jar"]
