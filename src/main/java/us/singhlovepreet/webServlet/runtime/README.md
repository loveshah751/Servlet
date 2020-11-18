<h1> How to Run the App Servlet Class.</h1>

<p> Open the Terminal or cmd (for Windows), and type <b>./gradlew run</b> and it will run the App Servlet.
Once you are successfully able to run the Application. it will be waiting for the client to be connected on port number
8080. you send the request from the browser with the url localhost:8080/signup and it will bring a signup form  for you.
Click submit and server will send you response back with the same information you enter.</p>

<p> What if our application suddenly crashed because of any possible reason. Then in this case our application should be
 able to gracefully shutdown all the open resource before terminating completely. What I mean by that is there might be a 
 possibility that some of resource are still open like current Thread is in the middle of DB Transaction, or 
 anything like socket connection is still open. So in order to close all the resource and freeing the memory, there is a
 support for gracefully shutdown hooks. This method will only be called when you click on stop button or OS SIGTERM signal
  to terminate the current process from the OS. </p>
  
  <h2> Note: This functionality is recently added in the springboot version 3.0 for gracefully shutdown and support more
   advanced feature with this functionality.</h2>