FROM java:8-jdk-alpine

COPY ./target/app-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app

RUN sh -c 'touch app-0.0.1-SNAPSHOT.war'

EXPOSE 27017


ENTRYPOINT ["java", "-jar", "app-0.0.1-SNAPSHOT.war"]
