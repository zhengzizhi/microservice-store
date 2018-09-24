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


## How to start this project
$ mkdir /opt/coding
$ cd /opt/coding && git clone https://github.com/zhengzizhi/microservice-store.git
$ docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"
$ docker-machine ls
$ cd /opt/coding/microservice-store
$ mvn clean && sh run.sh


## when .docker/machine/machines path not enough disk space

du -h --max-depth=1
docker-machine rm default
docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"

CentOS 7.5 profile :
[root@cloud ~]# cat /etc/profile
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
[root@cloud ~]# 


## your computer has poweroff or reboot, not to execute bash run.sh to start microservice-store

docker-machine start default
cd /opt/coding/microservice-store && docker-compose up -d 


## check docker container server logs for debugging

docker container ls --all
docker logs -f bc83190eed58
docker logs -f ee9d0560f5e0

[root@cloud microservice-store]# docker container ls --all
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
[root@cloud microservice-store]# 

## show all docker container server ip address

docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $(docker ps -aq)
docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)


[root@cloud microservice-store]# docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
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
[root@cloud microservice-store]# 

```

## License

This project is licensed under Apache License 2.0.
