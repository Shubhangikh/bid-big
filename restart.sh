#!/bin/bash

set -e
set -u

mvn clean package -Dmaven.test.skip=true -Dmaven.site.skip=true -Dmaven.javadoc.skip=true 
docker-compose -f docker-compose.light.yml -f docker-compose.dev.light.yml build
docker stop $(docker ps -aq)
docker-compose -f docker-compose.light.yml -f docker-compose.dev.light.yml up  > out.txt
