#!/bin/sh

exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "br.com.vfmneto.cashbackapi.CashbackApiApplication"  "$@"
