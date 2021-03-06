FROM amazoncorretto:11-alpine-jdk
ENV JAVA_OPTS "-XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler"
RUN mkdir -p /srv/
COPY /build/libs/CodeAnalyzerServer-0.0.1-SNAPSHOT-boot.jar /srv/app.jar
WORKDIR /srv
STOPSIGNAL SIGINT
ENTRYPOINT java $JAVA_OPTS -jar app.jar 
EXPOSE 8080
