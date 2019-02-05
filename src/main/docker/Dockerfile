FROM openjdk:8-jre-alpine

ENV APPLICATION_SPOTIFY_CLIENT_ID="" \
    APPLICATION_SPOTIFY_CLIENT_SECRET="" \
    JAVA_OPTS=""

RUN adduser -D -s /bin/sh cashback
WORKDIR /home/cashback

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown cashback:cashback entrypoint.sh
USER cashback

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

ADD *.war app.war