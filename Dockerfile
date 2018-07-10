FROM gradle as builder

COPY --chown=gradle:gradle . /home/gradle/src/hello-world-gr
WORKDIR /home/gradle/src/hello-world-gr
RUN gradle clean installDist

FROM openjdk:8-jre-slim
EXPOSE 8080
COPY --from=builder /home/gradle/src/hello-world-gr/build/install/hello-world-gr /app/
WORKDIR /app
CMD bin/hello-world-gr
