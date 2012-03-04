package com.googlecode.htmleasy.playground;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.View;
import com.googlecode.htmleasy.Viewable;

@Path("/time")
public class TheTime {
    

	@GET @Path("/now-using-jsp")
	public View currentTimeUsingJSP() {
		
		String now = SimpleDateFormat.getDateTimeInstance().format(new Date());

		return new View("/templates/playground/time/TimeDisplay.jsp", now);
	}
	
    @GET @Path("/now-using-soy")
    public View currentTimeUsingSoy() {
        
        String now = SimpleDateFormat.getDateTimeInstance().format(new Date());
        
        return new View("/soy/playground.time.TimeDisplay", ImmutableMap.of("time", now));
    }
    
    @GET @Path("/now-raw-text")
    public Viewable currentTimeRawText() {
    	
    	final String now = SimpleDateFormat.getDateTimeInstance().format(new Date());
    	
    	return new Viewable() {
			public void render(HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException, WebApplicationException {
				response.getWriter().write(now);
			}
		};
    }
    
    @GET @Path("/now-as-json")
    @Produces("application/json")
    public Map<String, ?> currentTimeJSON() {
    	
    	String nowString = SimpleDateFormat.getDateTimeInstance().format(new Date());
    	long nowLong = System.currentTimeMillis();
    			
    	return  ImmutableMap.of(
    			"timeString", nowString, 
    			"timeLong", nowLong
    			);
    }
    
    
    @GET
    public View index() {
        // If the user hits index, then redirect to a default view.
        // This demonstrates throwing a redirect exception targeting a method rather than a path.
        throw new RedirectException(TheTime.class, "currentTimeUsingJSP");
    }
    

}
