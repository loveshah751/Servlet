<h1> Request Object </h1>

<p>A Request object consist of multiple Attribute. For Instance A Sample Request from Web Browser or from any consumer 
of our custom web Server, which communicate to our Server via HTTP Protocol which rides on the top of TCP. Sample 
request will look like:</p>
<ul>
<li>
Protocol    :  HTTP/1.1 
</li>
<li>
MethodType :  GET/POST 
</li>
<li>
Host :  localhost:8081
</li>
<li>
URL PATH :  /user
</li>
<li>
Request Header :  text/html or application/json
</li>
<li>
Query String :  /user?name="Sam"&Age="23"
</li>
<li>
Request Body :  {"name" = "sam","age":23}
</li>
</ul>
<p> These are the attribute that are present in typical all types of request made to web server.
So we will create custom Request Object class for our project which include each of the attribute 
we discuss in the sample request.</p>
