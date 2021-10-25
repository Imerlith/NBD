#!/bin/sh
set -xe

docker run -d --rm --network=host -v `pwd`:/data --name="pjatk-nbd-mongo" mongo
docker exec pjatk-nbd-mongo mongoimport --file /data/cwiczenia2.json --db nbd --jsonArray -c people
docker exec -it pjatk-nbd-mongo bash
