FROM eclipse-temurin:17.0.3_7-jre-focal
WORKDIR /app


COPY order-management/target/order-management-0.0.1-SNAPSHOT.jar order-management.jar

ENV PORT 8097
EXPOSE $PORT

ENTRYPOINT java $JAVA_OPTS  -jar /app/order-management.jar
