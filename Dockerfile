FROM warleyvods/java17-with-newrelic

EXPOSE 9000
WORKDIR /app

ENV DATABASE_CONNECTION_URL=""
ENV SCOPE="prod"
ENV PASSWORD=""
ENV USER=""

COPY build/libs/anystore.jar /app/anystore.jar
CMD mkdir /app/files

ENTRYPOINT ["java", "-jar", "anystore.jar"]
