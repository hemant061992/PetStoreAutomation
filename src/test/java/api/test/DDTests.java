package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	public Logger logger;
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname,String useremail,String pwd, String ph )
	{
	   logger =LogManager.getLogger(this.getClass());
	   
	   logger.info("********** Creating user  ***************");
		
	   User payload=new User();
       
       payload.setId(Integer.parseInt(userID));
       payload.setUsername(userName);
       payload.setFirstName(fname);
       payload.setLastName(lname);
       payload.setEmail(useremail);
       payload.setPassword(pwd);
       payload.setPhone(ph);
       
       Response response=  UserEndPoints.createUser(payload);
       Assert.assertEquals(response.getStatusCode(), 200);
       
       logger.info("********** User is createed  ***************");
      
	}

	@Test(priority=2, dataProvider="userNames", dataProviderClass=DataProviders.class)
	public void testDeleteByUserName(String userName)
	{
		
	   logger.info("**********   Deleting User  ***************");	
	   
	   Response response=UserEndPoints.deleteUser(userName);
	   Assert.assertEquals(response.getStatusCode(),200);
	 
	   logger.info("********** User deleted ***************");
	   
	   
	}
}









