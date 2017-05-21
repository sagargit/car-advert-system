# car-advert-system

## Technology

The project is developed using `play 2.4` along with scala `2.11.8`.
`MongoDB 3.4` is used as a backend database.

## Requred Configuration
Along with `jdk`, `scala`,`sbt` and `play`, `mongo` should be installed and `mongod` service should be started. MongoDB can be installed from here: https://docs.mongodb.com/manual/installation/

## Running The Project
Project can be run using command:

    sbt run

Test case can be run using command:

    sbt test
    
## Api Conventions

### Json field Naming
Json field must be passed in snakecases. 
    
    For eg: firstRegeistration is written as first_registration

### Sorting
To sort, sorting information must be passed using request param as:

-> Sorting in `aescending order` can be done as:  
    
    .../adverts?sort={"first_registration":1}

-> Sorting in `descending order` can be done as:  

    .../adverts?sort={"first_registration":-1}

