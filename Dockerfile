FROM navikt/java:11
COPY build/libs/helse-nl-altinn-*.jar app.jar
ENV JAVA_OPTS="-Dlogback.configurationFile=logback-remote.xml"
ENV APPLICATION_PROFILE="remote"
