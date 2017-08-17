# RESTful API to calculate real time statistics from the last 60 seconds

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

Building a RESTful API to calculate real time statistics from the last 60 seconds.
There will be two APIs, one of them is called every time a transaction is made. It is also the sole input of this RESTful API.
The other one returns the statistic based of the transactions of the last 60 seconds.
 
POST /transactions

Every Time a new transaction happened, this endpoint will be called.

JSON Body:
{
"amount": 12.3,
"timestamp": 1478192204000
}

Where:
* amount - transaction amount - in double
* timestamp - transaction time in epoch in millis in UTC time zone (this is not current timestamp) - in long

Returns: 
Empty body with either 201 or 204.
* 201 - in case of success
* 204 - if transaction is older than 60 seconds

GET /statistics

This is the endpoint that returns the statistic based on the transactions which happened in the last 60 seconds.

Returns:
JSON Body
{
"sum": 1000,
"avg": 100,
"max": 200,
"min": 50,
"count": 10
}

Where:
* sum is a double specifying the total sum of transaction value in the last 60 seconds
* avg is a double specifying the average amount of transaction value in the last 60 seconds
* max is a double specifying single highest transaction value in the last 60 seconds
* min is a double specifying single lowest transaction value in the last 60 seconds
* count is a long specifying the total number of transactions happened in the last 60 seconds

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

# How to run

gradle bootRun

Use Postman (Chrome extension),
call endpoint 'localhost:8080/transactions' to POST and provide input JSON Body 'amount' and 'transaction' as mentioned in the problem document'.

Then call the endpoint 'localhost:8080/statistics' to GET statistics.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------