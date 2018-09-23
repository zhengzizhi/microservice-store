# microservice-store

## How to start this project
```
$ docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"
$ docker-machine ls
$ cd /opt/coding/microservice-store
$ mvn clean && sh run.sh
```

## when .docker/machine/machines path not enough disk space
```
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
```

## your computer has poweroff or reboot, not to execute bash run.sh to run microservice-store
```
docker-machine start default
cd /opt/coding/microservice-store && docker-compose up -d 
```

## check docker container server logs for debugging
```
docker container ls --all
docker logs -f f2417f378c11
```

## License

This project is licensed under Apache License 2.0.
