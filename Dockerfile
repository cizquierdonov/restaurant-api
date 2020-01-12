FROM gcr.io/google-appengine/openjdk:8

MAINTAINER SSP Team "Common Services"

EXPOSE 8080

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# Default copy (Gradle)
COPY ./build/libs/*.jar /api/app.jar

# Default workspace dir
RUN ls /api
WORKDIR /api

# no root execution
USER www-data

ENTRYPOINT [ \
    "java", \
    "-server", \
    "-Doracle.jdbc.timezoneAsRegion=false", \
    "-Dsun.net.inetaddr.ttl=60", \
    "-jar", \
    "/api/app.jar" \
     #"-Djava.security.egd=file:/dev/./urandom", \
     #"-Dspring.profiles.active=${ENVIRONMENT:local}", \
]