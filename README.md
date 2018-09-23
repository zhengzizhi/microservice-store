# microservice-store

## How to start this project
$ docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"
$ docker-machine ls
$ cd /opt/coding/microservice-store
$ mvn clean && sh run.sh

## when .docker/machine/machines path not enough disk space
du -h --max-depth=1
docker-machine rm default
reboot 
docker-machine create default --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"

## your computer has poweroff or reboot, not to execute bash run.sh to run microservice-store
docker-machine start default
cd /opt/coding/microservice-store && docker-compose up -d 

## check docker container server logs for debugging
docker container ls --all
docker logs -f f2417f378c11

## License

This project is licensed under Apache License 2.0.
