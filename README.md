<<<<<<< HEAD

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
docker logs -f f2417f378c11

## show all docker container server ip address

docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $(docker ps -aq)
docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)

```
>>>>>>> branch 'master' of https://github.com/zhengzizhi/microservice-store.git

## License

This project is licensed under Apache License 2.0.
