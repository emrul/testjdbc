
FROM findepi/graalvm:native AS build
COPY OexApp.jar .
COPY graal.json .
RUN native-image \
    --verbose \
    --enable-all-security-services \
    -H:ReflectionConfigurationFiles=graal.json \
    --report-unsupported-elements-at-runtime \
    --allow-incomplete-classpath \
    -H:IncludeResources=".*" \
    -jar OexApp.jar

#FROM scratch
FROM ubuntu:latest
EXPOSE 8081
COPY --from=build OexApp .
ENTRYPOINT ["/OexApp"]
CMD []