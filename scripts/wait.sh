#!/usr/bin/env sh

while ! curl --output /dev/null --silent --head caliber-config:8888; do
    echo "Waiting for the Config Server"
    sleep 3
done

/usr/bin/java -jar /service.jar
