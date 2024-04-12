# Distributed Systems

## Prerequisites

- Docker Desktop (go [here](https://www.docker.com/products/docker-desktop/))
- Minikube (go [here](https://minikube.sigs.k8s.io/docs/start/))
- Kubectl (go [here](https://kubernetes.io/de/docs/tasks/tools/install-kubectl/))
- Istio (go [here](https://istio.io/latest/docs/setup/getting-started/#ip))

## How To Run

0. Execute `mkdir /mnt/data` to create a folder for the database
1. Execute `minikube start` to start the cluster
2. Execute `eval $(minikube docker-env)` such that minikube can use docker images
3. Execute `./docker-build.sh` to build docker images
4. Execute `kubectl label namespace default istio-injection=enabled` to inject istio
5. Execute `kubectl apply -f microservices.yaml` to deploy microservices to the cluster
6. Execute `kubectl apply -f istio.yaml` to deploy istio to the cluster
7. Execute `kubectl port-forward svc/legacy 8080:8080` to expose the application
8. Go to the [app](http://localhost:8080/EShop-1.0.0)
9. Activate the dashboard (look [here](https://istio.io/latest/docs/setup/getting-started/#dashboard))

## More

- Execute `./kubernetes-remove.sh` to delete deployments and services
