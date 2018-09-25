# microservice-store version information
pom.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.contoso</groupId>
	<artifactId>microservice-store</artifactId>
	<packaging>pom</packaging>
	<version>1.0.0-SNAPSHOT</version>
	<name>microservice-store/parent</name>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.5.RELEASE</version>
		<relativePath />
	</parent>

	<modules>
		<module>inventory-service</module>
		<module>order-service</module>
		<module>account-service</module>
		<module>user-service</module>
		<module>payment-service</module>
		<module>config-service</module>
		<module>discovery-service</module>
		<module>online-store-web</module>
		<module>catalog-service</module>
		<module>edge-service</module>
		<module>shopping-cart-service</module>
		<module>hystrix-dashboard</module>
	</modules>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.version>1.8</java.version>
		<spring-cloud.version>Finchley.SR1</spring-cloud.version>
		<docker.image.prefix>zigoo</docker.image.prefix>
		<docker.plugin.version>1.1.1</docker.plugin.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<!-- upgrade spring-security-oauth2 to 2.3.3.RELEASE -->
		<dependency>
			<groupId>org.springframework.security.oauth</groupId>
			<artifactId>spring-security-oauth2</artifactId>
			<version>2.3.3.RELEASE</version>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

</project>
```
## How to configure Java's Maven development environment on CentOS 7
https://blog.csdn.net/zhengzizhi/article/details/81039627

## How to start this project
```
$ mkdir /opt/coding
$ cd /opt/coding && git clone https://github.com/zhengzizhi/microservice-store.git
$ docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"
$ docker-machine ls
$ cd /opt/coding/microservice-store
$ mvn clean && sh run.sh
```
## How to test to generate Token (acme:acmesecret BASE64 is YWNtZTphY21lc2VjcmV0)
In browser input the below URL to wait for callbacking URL(We need code=j38Quz):
```
http://192.168.99.100:8181/uaa/oauth/authorize?response_type=code&scope=openid&client_id=acme&redirect_uri=http://www.baidu.com/&state=xyz

wait for callbacking URL   https://www.baidu.com/?code=j38Quz&state=xyz

curl -i -X POST http://192.168.99.100:8181/uaa/oauth/token \
-H 'authorization: Basic YWNtZTphY21lc2VjcmV0' \
-H "Accept: application/json" \
-d "client_id=acme&client_secret=acmesecret&grant_type=authorization_code&code=j38Quz&redirect_uri=http://www.baidu.com/"
HTTP/1.1 200 
Cache-Control: no-store
Pragma: no-cache
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 24 Sep 2018 16:08:55 GMT
{"access_token":"9b27b162-5049-4bfd-ae7b-525da55b608b","token_type":"bearer","refresh_token":"43d05c89-f47b-4b32-836d-2482689c8c23","expires_in":43199,"scope":"openid"}
```

## when .docker/machine/machines path not enough disk space
```
du -h --max-depth=1
docker-machine rm default
docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"
```
CentOS 7.5 profile :
$ cat /etc/profile
```
# /etc/profile
...  ...  ......
...  ...  ......
...  ...  ......

export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-3.b13.el7_5.x86_64
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:$PATH
export PATH=/opt/apache-maven-3.5.4/bin:$PATH
export MAVEN_HOME=/opt/apache-maven-3.5.4
export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://192.168.99.100:2376"
export DOCKER_CERT_PATH="/root/.docker/machine/machines/default"
export DOCKER_MACHINE_NAME="default"
```



## your computer has poweroff or reboot, not to execute bash run.sh to start microservice-store
```
docker-machine start default
cd /opt/coding/microservice-store && docker-compose up -d 
```

## check docker container server logs for debugging
```
docker container ls --all
docker logs -f bc83190eed58
docker logs -f ee9d0560f5e0
```
$ docker container ls --all
```
CONTAINER ID        IMAGE                         COMMAND                  CREATED             STATUS              PORTS                                                      NAMES
bc83190eed58        zigoo/online-store-web        "java -Djava.securit鈥   2 hours ago         Up 2 hours          0.0.0.0:8787->8787/tcp                                     microservice-store_online-store-web_1
36b3a5ac5e07        zigoo/order-service           "java -Djava.securit鈥   2 hours ago         Up 2 hours                                                                     microservice-store_order-service_1
0218323b221c        zigoo/shopping-cart-service   "java -Djava.securit鈥   2 hours ago         Up 2 hours                                                                     microservice-store_shopping-cart-service_1
af8cd5c8ddf4        zigoo/account-service         "java -Djava.securit鈥   2 hours ago         Up 2 hours                                                                     microservice-store_account-service_1
116760a65b30        zigoo/edge-service            "java -Djava.securit鈥   2 hours ago         Up 2 hours          0.0.0.0:9999->9999/tcp                                     microservice-store_edge-service_1
ee9d0560f5e0        zigoo/user-service            "java -Djava.securit鈥   2 hours ago         Up 2 hours          0.0.0.0:8181->8181/tcp                                     microservice-store_user-service_1
dcc5691ca29d        zigoo/catalog-service         "java -Djava.securit鈥   2 hours ago         Up 2 hours                                                                     microservice-store_catalog-service_1
dd015cc19293        zigoo/inventory-service       "java -Djava.securit鈥   2 hours ago         Up 2 hours                                                                     microservice-store_inventory-service_1
8385bcf26acb        mysql:5.7                     "docker-entrypoint.s鈥   2 hours ago         Up 2 hours          0.0.0.0:3306->3306/tcp, 33060/tcp                          microservice-store_mysql_1
4d401e68ca02        mongo:3.3                     "/entrypoint.sh mong鈥   2 hours ago         Up 2 hours          0.0.0.0:27017->27017/tcp                                   microservice-store_mongo_1
c6aa334c9e99        neo4j:3.4                     "/sbin/tini -g -- /d鈥   2 hours ago         Up 2 hours          0.0.0.0:7474->7474/tcp, 7473/tcp, 0.0.0.0:7687->7687/tcp   microservice-store_neo4j_1
3100b32a51a5        zigoo/hystrix-dashboard       "java -Djava.securit鈥   2 hours ago         Up 2 hours          0.0.0.0:6161->6161/tcp                                     microservice-store_hystrix-dashboard_1
ff497980e4d9        redis:3.0                     "docker-entrypoint.s鈥   2 hours ago         Up 2 hours          0.0.0.0:6379->6379/tcp                                     microservice-store_redis_1
789aa7586bf6        zigoo/discovery-service       "java -Djava.securit鈥   2 hours ago         Up 2 hours          0.0.0.0:8761->8761/tcp                                     microservice-store_discovery-service_1
5f4e49e729a4        zigoo/config-service          "java -Djava.securit鈥   2 hours ago         Up 2 hours          0.0.0.0:8888->8888/tcp                                     microservice-store_config-service_1
```
 

## show all docker container server ip address
```
docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $(docker ps -aq)
docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)


$ docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
/microservice-store_online-store-web_1 - 172.17.0.16
/microservice-store_order-service_1 - 172.17.0.15
/microservice-store_shopping-cart-service_1 - 172.17.0.14
/microservice-store_account-service_1 - 172.17.0.13
/microservice-store_edge-service_1 - 172.17.0.12
/microservice-store_user-service_1 - 172.17.0.10
/microservice-store_catalog-service_1 - 172.17.0.11
/microservice-store_inventory-service_1 - 172.17.0.9
/microservice-store_mysql_1 - 172.17.0.7
/microservice-store_mongo_1 - 172.17.0.8
/microservice-store_neo4j_1 - 172.17.0.6
/microservice-store_hystrix-dashboard_1 - 172.17.0.5
/microservice-store_redis_1 - 172.17.0.4
/microservice-store_discovery-service_1 - 172.17.0.3
/microservice-store_config-service_1 - 172.17.0.2

```
## Google or Firefox browser and curl command terminals have direct access to catalog information without authorization

$ curl -i -X GET http://192.168.99.100:8787/api/catalog/v1/catalog
```
HTTP/1.1 200 
Date: Mon, 24 Sep 2018 12:27:59 GMT
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked

{
    "id": 4, 
    "catalogNumber": 0, 
    "products": [
        {
            "id": 3, 
            "name": "cf push awesome (Hoodie, Men's Medium)", 
            "productId": "SKU-64233", 
            "description": "<p>One of the great natives of the cloud once said \"<em>Production is the happiest place on earth for us - it's better than Disneyland</em>\". With this stylish Cloud Foundry hoodie you can push code to the cloud all day while staying comfortable and casual. <br /><br />  <strong>Cloud Native PaaS Collection</strong><br />  10% cloud stuff, 90% platform nylon<br />  Cloud wash safe<br />  Five nines of <em>comfortability</em></p>", 
            "unitPrice": 21.99, 
            "inStock": null
        }, 
        {
            "id": 0, 
            "name": "Best. Cloud. Ever. (T-Shirt, Men's Large)", 
            "productId": "SKU-24642", 
            "description": "<p>Do you love your cloud platform? Do you push code continuously into production on a daily basis? Are you living the cloud native microservice dream? Then rain or shine, this T-Shirt is for you. Show the world you're a stylish cloud platform architect with this cute yet casual tee. <br /><br />  <strong>Cloud Native Tee Collection</strong><br />  110% cloud stuff, 5% spandex<br />  Rain wash only<br />  Four nines of <em>stylability</em></p>", 
            "unitPrice": 21.99, 
            "inStock": null
        }, 
        {
            "id": 1, 
            "name": "Like a BOSH (T-Shirt, Women's Medium)", 
            "productId": "SKU-34563", 
            "description": "<p>The BOSH Outer Shell (<strong>BOSH</strong>) is an elegant release engineering tool for a more virtualized cloud-native age. The feeling of spinning up a highly available distributed system of VMs is second only to the feeling of frequently pushing code to production. Show the cloud <em>who's BOSH</em> with this stylish cloud native ops tee.<br /><br />  <strong>Cloud Native Tee Collection</strong><br />  99% YAML, 11% CLI<br />  BOSH CCK <span style='text-decoration: underline;'><em>recommended</em></span><br />  4 nines of <em>re-washability</em></p>", 
            "unitPrice": 14.99, 
            "inStock": null
        }, 
        {
            "id": 2, 
            "name": "We're gonna need a bigger VM (T-Shirt, Women's Small)", 
            "productId": "SKU-12464", 
            "description": "<i>\"Mr. Vaughn, what we are dealing with here is a perfect engine, an eating machine. It's really a miracle of evolution. All this machine does is swim and eat and make little containers, and that's all.\"</i>", 
            "unitPrice": 13.99, 
            "inStock": null
        }
    ], 
    "name": "Fall Catalog"
}
```
## How to connect redis
redis-cli -h 192.168.99.100 -p 6379

## How to use browser to login neo4j database
```
URL   http://192.168.99.100:7474/browser/
      username: neo4j
      password: secret
```

## How to execute SQL on named dev about mysql database
$ mysql -uroot -pdbpass -h192.168.99.100
```
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MySQL connection id is 62
Server version: 5.7.23 MySQL Community Server (GPL)

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MySQL [(none)]> use dev
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
MySQL [dev]> show tables;
+----------------------+
| Tables_in_dev        |
+----------------------+
| account              |
| account_addresses    |
| account_credit_cards |
| address              |
| cart_event           |
| catalog_info         |
| credit_card          |
| customer             |
| user                 |
+----------------------+
9 rows in set (0.002 sec)

MySQL [dev]> select * from user;
+----+------------+---------------+----------------------+------------+-----------+----------+
| id | created_at | last_modified | email                | first_name | last_name | username |
+----+------------+---------------+----------------------+------------+-----------+----------+
|  0 | 1537147813 |    1537147813 | john.doe@example.com | John       | Doe       | user     |
+----+------------+---------------+----------------------+------------+-----------+----------+
1 row in set (0.001 sec)

MySQL [dev]> select * from account;
+----+------------+---------------+----------------+-----------------+---------+
| id | created_at | last_modified | account_number | default_account | user_id |
+----+------------+---------------+----------------+-----------------+---------+
|  0 | 1537147823 |    1537147823 | 12345          |                | user    |
+----+------------+---------------+----------------+-----------------+---------+
1 row in set (0.001 sec)

MySQL [dev]> select * from account_addresses;
+------------+--------------+
| account_id | addresses_id |
+------------+--------------+
|          0 |            0 |
+------------+--------------+
1 row in set (0.001 sec)

MySQL [dev]> select * from account_credit_cards;
+------------+-----------------+
| account_id | credit_cards_id |
+------------+-----------------+
|          0 |               0 |
+------------+-----------------+
1 row in set (0.001 sec)

MySQL [dev]> select * from address;
+----+------------+---------------+--------------+-----------+---------------+-------+----------------------+---------+----------+
| id | created_at | last_modified | address_type | city      | country       | state | street1              | street2 | zip_code |
+----+------------+---------------+--------------+-----------+---------------+-------+----------------------+---------+----------+
|  0 | 1537147823 |    1537147823 |            0 | Palo Alto | United States | CA    | 3495 Deer Creek Road |         |    94304 |
+----+------------+---------------+--------------+-----------+---------------+-------+----------------------+---------+----------+
1 row in set (0.001 sec)

MySQL [dev]> select * from cart_event;
Empty set (0.001 sec)

MySQL [dev]> select * from catalog_info;
+----+--------+------------+
| id | active | catalog_id |
+----+--------+------------+
| 0  |       |          0 |
+----+--------+------------+
1 row in set (0.001 sec)

MySQL [dev]> select * from credit_card;
+----+------------+---------------+------------------+------+
| id | created_at | last_modified | number           | type |
+----+------------+---------------+------------------+------+
|  0 | 1537147823 |    1537147823 | 1234567801234567 |    0 |
+----+------------+---------------+------------------+------+
1 row in set (0.002 sec)

MySQL [dev]> select * from customer;
+----+------------+---------------+----------------------+------------+-----------+------------+
| id | created_at | last_modified | email                | first_name | last_name | account_id |
+----+------------+---------------+----------------------+------------+-----------+------------+
|  0 | 1537147823 |    1537147823 | john.doe@example.com | John       | Doe       |          0 |
+----+------------+---------------+----------------------+------------+-----------+------------+
1 row in set (0.001 sec)

MySQL [dev]> 
```
## How to push images to  https://hub.docker.com , for example push config-service image and discovery-service image to https://hub.docker.com
```
$ docker login

Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username: zigoo        ## this is my registered username
Password: ********     ## this is my login https://hub.docker.com website of password
WARNING! Your password will be stored unencrypted in /root/.docker/config.json.
Configure a credential helper to remove this warning. See
https://docs.docker.com/engine/reference/commandline/login/#credentials-store

Login Succeeded

$ docker push zigoo/config-service
The push refers to repository [docker.io/zigoo/config-service]
1cbd9cfec2f6: Pushed 
8a3a6492a95b: Pushed 
ad0b3bf520dd: Mounted from anapsix/alpine-java 
cd7100a72410: Mounted from anapsix/alpine-java 
latest: digest: sha256:197f92c8886eba4b72658bda3a8a97c7bad8a6b8cddaa73e192b7d2389ba65a0 size: 1164

$ docker push zigoo/discovery-service
The push refers to repository [docker.io/zigoo/discovery-service]
7c3b5acfb428: Pushed 
c67c3ccd72d1: Pushed 
ad0b3bf520dd: Mounted from zigoo/config-service 
cd7100a72410: Mounted from zigoo/config-service 
latest: digest: sha256:848d1e9bdd3bfa13b65f85ef4db0831d729c4b9637fe18ea3939c51dcd108ad8 size: 1164
```

## License

This project is licensed under Apache License 2.0.
