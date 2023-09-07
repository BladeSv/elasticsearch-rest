FROM amazoncorretto:17 as build_docker
RUN mkdir -p /elastic
COPY . /elastic
WORKDIR /elastic
RUN ./gradlew clean bootJar && cp ./build/libs/elasticsearch-rest-0.0.1-SNAPSHOT.jar elastic.jar

FROM amazoncorretto:17
COPY --from=build_docker /elastic/elastic.jar elastic.jar
CMD ["java", "-jar", "elastic.jar"]