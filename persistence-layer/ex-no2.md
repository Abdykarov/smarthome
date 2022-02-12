# Liquibase vs. Flyway

### Which one do you like better? Why do you need database migration anyway? What do you think about the evolutionary database design?

Unfortunately I don't have any experience with Liquibase, but i do have with Flyway. Despite i made some research on liquibase and made my own comparison between them.
Liquibase supports XML, YAML and JSON scripts to update database, while Flyway support only SQL scripts. Flyway works perfectly with dbs like Oracle or PostgresSQL, because uses SQL, on the other hand Liquibase makes abstract layer, so it can be implemented with different databases.

Migration helps to move data from one db to another, it is very helpful tool, because once you wrote sql scripts, you dont need to do that everytime. Additionally, it is possible to generate some startup data for different purposes.

I think evolutionary database design is necessary nowadays. Since it built on agile techniques, design helps developers and db developers work together as one team. It makes possible to database scheme to evolute, before that once database scheme was created it was not possible to update it without breaking system functionality.
