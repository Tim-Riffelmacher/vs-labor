#!/bin/sh
kubectl delete service user category product legacy web-shop-db-image
kubectl delete deployments user category product legacy
kubectl delete secret mysql-secret 
kubectl delete configmap mysql-cm
kubectl delete statefulset mysql-ss 
kubectl delete persistentvolumeclaim mysql-pvc
kubectl delete persistentvolume mysql-pv
kubectl delete virtualservice istio-vs
kubectl delete gateway istio-gateway