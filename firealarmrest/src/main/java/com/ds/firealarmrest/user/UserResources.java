package com.ds.firealarmrest.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ds.firealarmrest.sensor.Sensor;

/*** APIs *****/

@Path("user")
public class UserResources {
    private UserRepository repository = new UserRepository();

    /***** API for get user name and password when logging to the system  *****/
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        return repository.login(userName, password);
    }
    
    /***** API for create user to register to the system  *****/
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(User u1) {
//        if (u1.getFirstName() == null || u1.getLastName() == null || u1.getUserName() == null || u1.getEmail()==null || u1.getPassword() == null )
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        else
            return repository.Registeruser(u1);
    }
}
