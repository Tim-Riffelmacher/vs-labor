# Distributed Systems

## Prerequisites

- Docker Desktop (go [here](https://www.docker.com/products/docker-desktop/))
- Minikube (go [here](https://minikube.sigs.k8s.io/docs/start/))
- Kubectl (go [here](https://kubernetes.io/de/docs/tasks/tools/install-kubectl/))

## How To Run

0. Execute `mkdir /mnt/data` to create a folder for the database
1. Execute `minikube start` to start the cluster
2. Execute `eval $(minikube docker-env)` such that minikube can use docker images
3. Execute `./docker-build.sh` to build docker images
4. Execute `kubectl apply -f microservices.yaml` to deploy microservices to the cluster
5. Execute `kubectl port-forward svc/legacy 8080:8080` to expose the application
6. Go to the [app](http://localhost:8080/EShop-1.0.0)

## More

- Execute `./kubernetes-remove.sh` to delete deployments and services
