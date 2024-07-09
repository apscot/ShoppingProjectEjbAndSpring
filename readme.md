# Shopping Cart Project for Ericsson

Find the batch file named run.bat which will directly build all three application and create their individual containers. It will also create a common network and register all three in the same network so that inter service communication is achieved. 

**PreRequisite: Java 11, Docker should be installed in your system.**

Use this file as the release note for this project:

Few things I would like to mention here: I have done few assumtions based on the project details given.

```
1. All get API had a input payload but as per Rest API guidlines we should not use Post/Put for getting/fetching anything from an API. So as GetMapping doesnt allow input payload to be passed, I kept the input details as path params.
2. I used H2 database for inmemory db for Product and ShoppingCart application.
3. I have created my own LRU cache implementation which evicts within 30 secounds of inactivity.
4. Unfortunately time was very short, So I have missed the test case for the customershoppingcart application.
5. My EJB knowledge was not quite up to the mark so did bare minimum for EJB implementation.
6. Didnt put any Exception handling as it was not mentioned the description, given few Sysouts, wherever needed.
```

Thanks a lot for this beautiful project, I really enjoyed working on it. please try to ignore if any silly mistakes found. Got a very short time to complete, did this in 1.5 days. I am proud of what I achieved in this short time. Hope you will consider my job application. 


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

# Please find screenshots below.
## Products API
![image](https://github.com/apscot/ShoppingProjectEjbAndSpring/assets/756039/dfceff7b-9e27-4f30-8274-f7ec8016d895)
## GetShoppingCarts API
![image](https://github.com/apscot/ShoppingProjectEjbAndSpring/assets/756039/2c7c8642-5359-49e6-9d10-24b5a961505f)
## CustomerShoppingCart API
![image](https://github.com/apscot/ShoppingProjectEjbAndSpring/assets/756039/ebcf4509-6c4a-4fd4-a284-5dcb44d1d989)
## Caching put, get delete and scheduler logs
![image](https://github.com/apscot/ShoppingProjectEjbAndSpring/assets/756039/208933e8-02c4-4d4c-a4fc-b0690bfdc03f)



