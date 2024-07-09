@ECHO OFF
docker network create myNetwork
cd ProductProject
call gradle bootjar
call docker build -t productproject .
start docker run --name=productproject -p 8090:8090 productproject
cd ../ShoppingCartProject
call gradle bootjar
call docker build -t shoppingcartproject .
start docker run --name=shoppingproject -p 9090:9090 shoppingcartproject
cd ../customershoppingcart
call gradle build
call docker build -t customershoppingcartapp .
start docker run --name=customershoppingcart -p 8080:8080 customershoppingcartapp
timeout /t 15
docker network connect myNetwork productproject
docker network connect myNetwork shoppingproject
docker network connect myNetwork customershoppingcart
