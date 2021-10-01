# wetday

clone the repository

git clone https://github.com/bartmcd/wetday.git

In wetday/connectstart run 
'mnv clean install' 
and 
'mvn spring-boot:run'(to start sertver) 

In wetday/connect4jclient run 
'mnv clean install' 

Then open 2 terminals and run 
'mvn exec:java' 
in each terminal 


By default the server runs on port 9002 on the same host as the clients, the port can be changed in  'src/main/resources/application.properties'
server.port=9002

If the port is changed the the client can be run against the new host port combination as below 

mvn exec:java -Dexec.args="localhost:9003"


By default The server API can be viewed at 

http://localhost:9002/swagger-ui.html



The application is not complete
- At user disconnection the game is not cleared down, so another game could potentially start wihout restarting the server.
- https is not supported(only http required)
- Persistance of the game state at server shutdown is not supported.
- Logging and error handling in the client is not complete.
- Some unit tests are provided but more would be needed.
