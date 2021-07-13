## TP TACS
## GRUPO 3
[![codecov](https://codecov.io/gh/tp-tacs-grupo-03/hero-cards-game-api/branch/main/graph/badge.svg?token=iwngAylNUA)](https://codecov.io/gh/tp-tacs-grupo-03/hero-cards-game-api)
[![CI](https://github.com/tp-tacs-grupo-03/hero-cards-game-api/actions/workflows/build.yml/badge.svg)](https://github.com/tp-tacs-grupo-03/hero-cards-game-api/actions/workflows/build.yml)

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
* Super Hero API token. Create locally `prod.env` file and add your token 
in the property `super_hero_api_key`. Example `super_hero_api_key=1231231`
### Check the Backend using the following URL
* [SWAGGER LINK](http://localhost:8080/swagger-ui.html) 
