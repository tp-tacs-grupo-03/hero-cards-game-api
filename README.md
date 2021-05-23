## TP TACS
## GRUPO 3

### How to install
#### Requirements technology stack
* Docker [Ubuntu Instructions](https://docs.docker.com/engine/install/ubuntu/)
* Docker Compose [Instructions](https://docs.docker.com/compose/install/)
* JVM 11 [Ubuntu Instructions](https://www.infoworld.com/article/3514725/installing-oracle-java-se-11-on-ubuntu-18-04.html)


#### How to build and run the project locally
* Clone the repository. `git clone https://github.com/tp-tacs-grupo-03/hero-cards-game-api`
* Move to folder `cd hero-cards-game-api` 
* Build the image `docker-compose build`
* Run the image `docker-compose up`

### Check the Backend using the following URL
* [SWAGGER LINK](http://localhost:8080/swagger-ui.html) 

### Database check setup
* Use this cmd to connect in database and check dbname 
* `docker exec -it database bash`
* `psql --host=database --username=postgres --dbname=hero_cards_game`  
