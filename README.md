# InvoiceService

1.) Use application.properties located in main/test resources to configure the application.
To run tests with in-memory db provide h2 profile : spring.profiles.active=h2
To run tests using standalone MYSQL db please provide datasource setup as follows:

spring.datasource.url = jdbc:mysql://localhost:3306/invoicing
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.username = root
spring.datasource.password = changeme01

and switch to defaul profile by removing spring.profiles.active property from config.

2.) To run tests start AllTestsRunner which runs both unit and integration tests.

3.) Wheneven you run test with h2 profile database schema is created using dbschema.sql and filled with test data from dbdata.sql.

4.) If you run application in non-test mode please setup mysql database using provided sql script: dbschema.sql.
