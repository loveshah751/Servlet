
<h1> Servlet LifeCycle </h1>

<h3> Steps after the Development  </h3>

<ul>
<li>Once we are done with development of our server side web project.
 Its time to run our project. But hold on, our project is server side, so we need some
 kind of server on which our project can run right?</li>
 <li> Yes, In order to run the web Project we need server. And there are different types
 of servers available in the market. For Instance, very famous TOMCAT, GLASSFISH, JETTY etc.
 </li>
</ul>

<pre> For this project we are using the Tomcat Server. And Once we are done with development we will create
 a WAR file of your project which will include all the <b>.class</b> files of the project. After the creation of WAR 
 file we will deploy it to the Tomcat Server. So that Server can run our web project and any client application, 
 for instance Web UI(Front-end), PostMan, YARC (yet Another Rest Client) can access the project. </pre>

<h2> Step by Step Explanation to Servlet Life Cycle.</h2>

<ul>
<li> Step 1 Once the Tomcat is starting, it will load all the .class files from the WAR file, and will create a Bean(instance)
based on the Tomcat configuration.</li>
<li> There are two ways tomcat create a bean of the servlet class. First one is once we hit the endpoint of the servlet
tomcat will create a bean for that. which is default configuration in the tomcat. </li>

<li>And Another one is <b>load-on-start-up</b>, which can have any value starting from 0. Servlet with lower value will be 
bootstrapped first. Also, default value for <b>load-on-start-up is -1.</b></li>

<li> All the Bean created by tomcat is Singleton, which means one instance per entire lifecycle of tomcat.</li>

<li> Step 2 Once the Bean creation is done, tomcat will call init() method to initialize any configuration we want to be loaded
 before our Servlet will start any processing of request. </li>
 
 <li> Step 3 is Tomcat will call the Service method within the HTTPServlet class. which will handle how to process any request
  coming from the client. Please refer to this link <a href="https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html#service-javax.servlet.http.HttpServletRequest-javax.servlet.http.HttpServletResponse-"
   >HTTP SERVLET</a>.</li>
   
   <li> Step 4 is Destroy method, which will called by tomcat once we request to stop the tomcat. This Method will do clean up or
   any connection closing stuff.</li>
 <li> Step 1,2 and 4 will be called only once per lifecycle. But the Step 3 will be called for every request made by the client.</li>
</ul>

