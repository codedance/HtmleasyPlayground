package com.googlecode.htmleasy.playground;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;

import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.View;
import com.googlecode.htmleasy.playground.domain.SignupDetails;
import com.googlecode.htmleasy.playground.domain.SignupDetails.Sex;
import com.googlecode.htmleasy.playground.domain.ValidateResponse;

@Path("/signup")
public class UserSignup {

	/** Simple POJO to store our view model. */
	public class Model {
		private SignupDetails signupDetails = new SignupDetails();
		private List<String> errors = new ArrayList<String>();
		
		public SignupDetails getSignupDetails() {
			return signupDetails;
		}

		public List<String> getErrors() {
			return errors;
		}
	}
	

	@GET
	public View showSignupForm() {
		return new View("/templates/playground/signup/form.jsp", new Model());
	}
	
	
	@POST
	public View processSignup(@Form SignupDetails signupDetails) {
		
		Model model = new Model();
		model.signupDetails = signupDetails;
		
		// Validate username (reuse the same method used by AJAX)
		ValidateResponse response = usernameValidator(signupDetails.getUsername());
		if (!response.isValid()) {
			model.errors.add(response.getMessage());
		}
		
		// Validate password
		if (signupDetails.getPassword().length() < 5) {
			model.errors.add("You need a stronger password!");
		} else if (!signupDetails.getPassword().equals(signupDetails.getConfirmPassword())) {
			model.errors.add("Your password does not match!");
		}
		
		// Validate sex
		if (signupDetails.getSex() == Sex.Unknown) {
			model.errors.add("Please select a sex.");
		}
		
		// Validate Legal
		if (!signupDetails.isAgreedLegal()) {
			model.errors.add("You must agree to the legal terms.");
		}
		
		// If valid then process and forward to the thanks page.
		if (model.errors.size() == 0) {
			// do stuff ... userService.createUser(signupDetails)
			throw new RedirectException("/signup/thanks");
		}
		
		// Invalid. Show form again with errors populated in the model.
		return new View("/templates/playground/signup/form.jsp", model);
	}
	
	
	@GET @Path("/thanks")
	public View showSignupThanks() {
		return new View("/templates/playground/signup/thanks.jsp");
	}

	
	/**
	 * Validate a username server-side to ensure it's suitable for the signup process.
	 * 
	 * This method is exposed to the web view layer via AJAX as well as used directly 
	 * in Java when processing the post.
	 * 
	 * Note: Of course in a real application some of the validation in this method would
	 * be done client-side, however some tasks such as the availability of a username
	 * can only be done server-side.
	 * 
	 * @param username 	The username to validate.
	 * @return A response object containing the validation status and a message.
	 */
	@Path("/validate_username") @POST
	@Produces("application/json")
	public ValidateResponse usernameValidator(@FormParam("username") String username) {
		
		if (username == null || username.length() == 0) {
			return new ValidateResponse(false, "Please enter a username.");
		} else if (username.startsWith("admin") || username.equals("john")) {
			return new ValidateResponse(false, "This username is already taken.");
		} else if (username.length() < 4) {
			return new ValidateResponse(false, "Usernames must be 4 or more letters.");
		} else if (username.contains(" ")) {
			return new ValidateResponse(false, "Usernames must not contain spaces.");
		}
		
		return ValidateResponse.VALID;
	}

}
