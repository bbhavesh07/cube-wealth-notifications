[Note: You can directly jump to the command "mvn clean install" if you have completed mysql and rabbitmq setup in "cube-wealth" setup]

docker pull mysql:8

docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cubewealth -d mysql:8

docker pull rabbitmq:3-management

docker run -p 15672:15672 --name rabbitmq -d rabbitmq:3-management

mvn clean install

docker build . -t cube-wealth-notifications

docker run -p 9091:9091 --name cube-wealth-notifications --link mysql-standalone:mysql --link rabbitmq:rabbitmq -d cube-wealth-notifications