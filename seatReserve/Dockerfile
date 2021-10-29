FROM java:8-jdk-alpine

COPY ./target/seatReserve-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app

RUN sh -c 'touch seatReserve-0.0.1-SNAPSHOT.war'

EXPOSE 27017

ENTRYPOINT ["java", "-jar", "seatReserve-0.0.1-SNAPSHOT.war"]
