# car-advert-system

## Technology

The project is developed using `play 2.4` along with scala `2.11.8`.
`MongoDB 3.4` is used as a backend database. Test case is written using `specs2`.

## Requred Configuration
Along with `jdk`, `scala`,`sbt` and `play`, `mongo` should be installed and `mongod` service should be started. MongoDB can be installed from here: https://docs.mongodb.com/manual/installation/

## Running The Project
Project can be run using command:

    sbt run

Test case can be run using command:

    sbt test
    
## Api Conventions

### Json field Naming
Json fields should be written in snakecases. 
    
    For eg: field firstRegistration is written as first_registration

### Sorting
To sort, sorting information can be passed using Request Param as:

-> Sorting in `aescending order`  
    
    .../adverts?sort={"first_registration":1}

-> Sorting in `descending order`  

    .../adverts?sort={"first_registration":-1}

