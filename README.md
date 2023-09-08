# Elasticsearch-rest

## Run guide

Docker require for staring this project.

### Tasks:

- Task 3. Branch ``main``
- Task 4. Branch ``task4``

For start ``docker-compose up --build`` ('--build' require for rebuild app after switching branches )

Web UI (swagger) http://localhost:8080/swagger-ui/index.html

When project up you need to run endpoint ``GET /employees-controller/fillEmployeeDb`` in swagger for fill employee index
the start data.

## 3 - Implement Java Low Level REST Client for retrieval of employees info

[Java Low Level REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/java-rest-low.html)

```
1. Get all employees.

2. Get an employee by id.

3. Create an employee providing id and employee data json.

4. Delete an employee by its id.

5. Search employees by any field. Field name and field value should be provided as http request parameters.
   Implement the endpoint and try to get all employees with Java skill. 
   Make choise between "Match" and "Term" query.

6. Perform an aggregation by any numeric field with metric calculation. 
   Aggregation field, metric type and metric field should be provided as http request parameters.


Implementation details:

* Your Java application should contain controller, service and dto layers.
* Use jackson ObjectMapper to serialize and deserialize documents to Employee Java instance.
* Add Swagger to your application. 
* Make screenshots of some requests/responses performed.
```

## 4 - Implement Java API Client for retrieval of employees info (1 Point)

[Java API Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)

```
Perform the same tasks as for Java Low REST Client.
Note! You should use Java API Client, not the deprecated High Level Rest Client. They are similar, so don't mistake one for the other.
What difficulties have you faced comparing to Java Low REST client?
```
