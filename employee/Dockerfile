FROM java:8-jdk-alpine

COPY ./target/employee-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app

RUN sh -c 'touch employee-0.0.1-SNAPSHOT.war'

EXPOSE 27017

ENTRYPOINT ["java", "-jar", "employee-0.0.1-SNAPSHOT.war"]
