FROM openjdk:11-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/hosptal-management-system.jar
WORKDIR /app
ENTRYPOINT java -jar hosptal-management-system.jar