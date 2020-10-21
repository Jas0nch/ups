FROM java:openjdk-8-jdk-alpine
VOLUME /tmp
ADD libs/*.jar app.jar

# set time zone
ENV TZ=America/New_York
RUN ln -snf /usr/share/zoninfo/${TZ} /etc/localtime && echo ${TZ} > /etc/timezone

EXPOSE 8081
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
