#!/bin/bash

export DATAFLOW_VERSION=2.2.1.RELEASE
export SKIPPER_VERSION=2.1.2.RELEASE

docker-compose \
    -f docker-compose.yml \
    -f docker-compose-prometheus.yml \
    -f docker-compose-rabbitmq.yml \
    up

#docker-compose \
#    -f docker-compose.yml \
#    -f docker-compose-prometheus.yml \
#   -f docker-compose-rabbitmq.yml \
#    -f docker-compose-postgres.yml \
#    up

