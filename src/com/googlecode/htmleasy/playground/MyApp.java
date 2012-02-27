package com.googlecode.htmleasy.playground;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.googlecode.htmleasy.HtmleasyProviders;

/**
 * Register our JAX-RS annotated classes and Htmleasy.  We'll do this explicitly rather 
 * than relying on classpath scanning as this is slow and affects startup times on GAE.
 * @author Chris
 *
 */
public class MyApp extends Application {

	 public Set<Class<?>> getClasses() {
	      Set<Class<?>> myServices = new HashSet<Class<?>>();
	      
	      // Add my own JAX-RS annotated classes
	      myServices.add(TheTime.class);
	      myServices.add(UserSignup.class);
	      myServices.add(Boats.class);
	      
	      // Add Htmleasy Providers
	      myServices.addAll(HtmleasyProviders.getClasses());
	      
	      return myServices;
	 }
}
