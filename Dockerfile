FROM gradle as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle installShadowDist

FROM openjdk:8-jre-slim
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/install/hello-world-gr-shadow /app/
WORKDIR /app
CMD bin/hello-world-gr
