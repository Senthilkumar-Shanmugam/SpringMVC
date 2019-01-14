Content negotiation can happen at server or client side.
  In the server-driven negotiation, a server process is responsible for selecting best representation for the response.
  For client-driven content negotiation, the client made the selection for the resource representation.
  REST API implementations work based on client-driven content negotiation
  
"Content Negotiation using HTTP Headers"
========================================

HTTP headers provide following 2 type of information for the negotiation

Content-Type: This header tells about the media type of the body of the request.
Accept header: Determine what type of representation required on the client side

2.1 Content-Type Header
Content-Type header helps in determining the type of the incoming request (e.g XML, JSON).This header helps server or the client to select the correct approach to handle and eventually parse the data by selecting right converter.Some of the known Content-Type headers are

application/xml
text/plain
application/json
Content-Type: application/json
POST /customers HTTP/1.1
Content-Type: application/json

{
  "lastName": "Fielding",
  "firstName": "Roy"
}

There are other content headers that can be used with Content-Type header

Content-Encoding: The header indicates what other content encodings applied to the payload.
Content-Language:  The header describes the natural language.
Server or client can send 415 status code(Unsupported media type) if they do not support the format sent in the request.

Client or calling API can specify data format they accept in the response by using Accept header.

Accept: application/json

//complete example
GET /customers HTTP/1.1
Accept: application/json

HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "firstName": "Umesh",
    "lastName": "Awasthi"
  }
]
Copy
Server API use default content mechanism if no Accept header is present in the request.There are other content headers that can be used with Accept header

Accept-Language: List of acceptable languages.
Accept-Encoding: List of acceptable encodings.
Accept-Charset: Acceptable charset list.
If the server cannot send data in a format requested in the Accept header, the server sends the 406 Not Acceptable error.

Content Negotiation using URL Extension
URL extension or suffix is another content negotiation strategy used by multiple systems.Client API can use the extension or suffix in the resource URI to inform expected content type. Let’s take an example to understand this strategy.

https://www.javadevjournal.com/v1/customers/1234.xml
https://www.javadevjournal.com/v1/customers/1234.json
Copy
The first request will return XML response while the second request is for the JSON data.
Similar to the Accept header strategy, server API use default content mechanism when the extension is not provided in the URL

 


 
