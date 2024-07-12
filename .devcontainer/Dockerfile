# Dockerfile for the ultimate development environment. Don't be lazy, customize it.
FROM openjdk:17-slim-bullseye

# Who's the boss?
LABEL maintainer="Rodrigo Dantas"
LABEL email="rodrigo.dantas@hustletech.dev"

# Installing a bunch of stuff because every ninja need some tools
RUN apt-get update && apt-get install -y maven git python3 unzip curl coreutils

# Git config because I'm too lazy to type it every time
RUN git config --global user.name "Rodrigo Dantas" && \
   	git config --global user.email "rodrigo.dantas@hustletech.dev" && \
    git config --global http.sslVerify false && \
    git config --global http.postBuffer 1048576000

# Adding timezone setting to JVM options
ENV JAVA_OPTS="-Duser.timezone=UTC"

# Setting our workspace, where the magic happens
WORKDIR /workspace

# Keep the container running forever, because we use devcontainers!
CMD ["tail", "-f", "/dev/null"]

# Exposing the most cliché port ever
EXPOSE 8080 
