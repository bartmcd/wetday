# wetday

Use the following to clone the repository

git clone https://github.com/bartmcd/wetday.git

In the directory wetday/connectstart run 
'mnv clean install' 
and 
'mvn spring-boot:run'(to start server) 

In the wetday/connect4jclient run 
'mnv clean install' 

Then to play the game, for each Player(User), open a terminal in the directory wetday/connect4jclient and run 
'mvn exec:java' 

The server port can be changed in 'src/main/resources/application.properties'.

The default setting is server.port=9002.

If the port is changed or the host is not local, the the client can be run against the new host port combination as below(for port 9003).

mvn exec:java -Dexec.args="localhost:9003"

The server rest API can be viewed at (for default host:port)

http://localhost:9002/swagger-ui.html


The application is not complete
- At user disconnection the game is not cleared down, so the server needs to be restarted for a new game.
- https is not supported.
- Persistance of the game state at server shutdown is not supported.
- Logging and error handling in the client is not complete.
- Some unit tests are provided but more would be needed.
