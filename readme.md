Step#1: First Create a docker network:
```
docker network create myNetwork
```
Step#2: Go to each folder and then build gradle, run docker build and then docker run:

```
cd ProductProject
gradle bootjar
docker build -t productproject .
docker run --name=productproject -p 8090:8090 productproject
```
```
cd ../ShoppingCartProject
gradle bootjar
docker build -t shoppingcartproject .
docker run --name=shoppingproject -p 9090:9090 shoppingcartproject
```
```
cd ../customershoppingcart
gradle build
docker build -t customershoppingcartapp .
docker run --name=customershoppingcart -p 8080:8080 customershoppingcartapp

```
Step#3: Add all these containers to newly created docker network.
```
docker network connect myNetwork productproject
docker network connect myNetwork shoppingproject
docker network connect myNetwork customershoppingcart
```
