FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ADD target/*.jar /usr/share/demo2.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "/usr/share/demo2.jar"]