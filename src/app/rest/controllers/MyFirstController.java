package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


// test using browser address http://127.0.0.1:9999/testcontroller/hello or /bye

@Component
@Path("/myfirstcontroller")
public class MyFirstController {
	
	
//	Logger logger = LoggerFactory.getLogger(MyFirstController.class);
	
	@GET
	@Path("/hello")
	public String sayHello(@QueryParam("n") String name, 
			               @QueryParam("a")int age)
	{

		return "Hello "+name+",age "+ age;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Path("/buytoy")
	public String buyToy(@FormParam("cc") String creditcard,
			             @FormParam("amt") double amount)
	{
		
		return "Kaching charged"+ creditcard + amount;
	}

}
