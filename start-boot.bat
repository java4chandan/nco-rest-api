::java -jar target/nco-rest-api-0.1.jar --server.port=8085
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n -jar target/nco-rest-api-0.1.jar --server.port=8085