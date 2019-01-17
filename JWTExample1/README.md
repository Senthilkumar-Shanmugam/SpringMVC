Restart your Application and open Postman to send some request. Let’s create new user by sending POST request to http://localhost:8080/user/register with the following request body:

{
 “name”: “Ihor Sokolyk”,
 “email”: “ihor@gmail.com”,
 “password”: “password”
}

We should get a token in the response body:

“eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1MTA0OTgwNDIsInVzZXJJZCI6IjVhMDg1ZWY5OTZlZTE3MWE4NDcwMmU1NiJ9.Q_kKBHy5A-pKp-NjaottM6QybwnTZ4QD2XBzOdDSVcs”

Now copy this token and let’s try to get just created user by making GET request to http://localhost:8080/user/get, but do not forget to add our token as Authorization header:

