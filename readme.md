Application is used for booking the cabs smartly and quick.

This is developed using spring boot and gradle.Database used is postgres.

DB scripts for the same as stored in resources/sql/db.sql

CabBookingApplication is rest controller used for the making cab booking and marking the trip complete.
For validation we are using spring boot validation framework and for persistence spring jpa is used.

Right now , we are using maths formula to compute the distance.
We can use google maps api for the same to get the traffic details too.

It can be tested using postman. For booking cab , below are the details 
POST : 
http://localhost:8080/book/bookcab

Request : {"customerId":"RLP","latitude":19.0633,"longitude":72.9989}

For fetching the driver details :

GET :
http://localhost:8080/book/fetchdriverdetails?page=0&size=10

This is the paginated get service. It returns the current page details along 
with the total records and the total pages 

For marking the trip complete

POST : http://localhost:8080/book/completetrip

{
            "driverId": "KAUSTUBH",
            "customerId": "XYZ",
            "driverLatitude": 19.0633,
            "driverLongitude": 72.9989,
            "availabilityStatus": "BUSY"
} 

Test cases have been added in the test folder for the basic use cases along with the data scripts . These are the integration test cases with db calls using spring boottest annotations. 

Improvements that can be implemented :

1) use google maps api
2) Maintaining Audit history
3) Implement Kafka/Spark for aysnc activities for reporting or data lake
