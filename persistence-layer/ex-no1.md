### What are your thoughts on lazily initialized collections in JPA? 
### What are the caveats, why would you use them?

Lazely initialized collections designed to load its elements when they needed. However, sometimes we cant access data of
lazely initialized collections when they are not in persistence context, it will throw exception. In order to avoid this, exists several ways in jpa,

1) Loading with JPA Query language - We can write jpql request to the db, using JOIN FETCH keyword, thus request will return owner entity and inverse entity's required attributes. 
2) Loading data with Entity graph - We can choose fields which entity graph will return, then entity graph will create sql request based on fields