FROM maven:3.9.7-eclipse-temurin-17-focal as build

WORKDIR /app

COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -Dmaven.test.skip=true

FROM build as prepare-production

RUN mkdir -p target/dependency
WORKDIR /app/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal

#RUN apt update
#RUN apt install redis
#RUN redis-server --daemonize yes

ARG DEPENDENCY=/app/target/dependency
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.leiyx.microchat.configserver.Application"]