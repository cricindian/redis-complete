# A demo application for redis operations, using spring boot.

All the below operations are covered as part of this demo.

* **`Storgin as Hash Map`** : Saving a Product object to redis as a hash.
* **`accessing`** : Get a Product by its ID.
* **`Storing as set`** :  Saving product information as a set to use in instant search.
* **`Instant search`** :  Using Redisearch to query and fetch results.

## Required dependencies
```
//Redis starter
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

//Jedis
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>

//For Instant search operation
```
