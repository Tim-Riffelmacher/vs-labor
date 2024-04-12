#!/bin/sh
docker build --no-cache --tag=vs-image-user:1.0.0 user

docker build --no-cache --tag=vs-image-category:1.0.0 category

docker build --no-cache --tag=vs-image-product:1.0.0 product

docker build --no-cache --tag=vs-image-legacy:1.0.0 legacy -f legacy/docker/Dockerfile

docker build --no-cache --tag=vs-image-db:1.0.1 legacy -f legacy/docker/DockerfileMySQL