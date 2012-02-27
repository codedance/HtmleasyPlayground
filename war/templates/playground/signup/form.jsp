
<%@page import="com.googlecode.htmleasy.playground.UserSignup"%>

<html>
<head>
    <style type="text/css">
      #usernameMsg {color: red;}
    </style>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	<script  type="text/javascript">
		// User JQuery to wire up call to the AJAX username validate method
		$(function () {

			$("#username").keyup(function (e) {
				var $element = $(this);
				$.ajax({
					type: "POST",
					url: "/signup/validate_username",
					data: {"username" : $element.val()},
					success: function (response) {
							if (!response.valid) {
								$("#usernameMsg").text(response.message);
							} else {
								$("#usernameMsg").text("");
							}
						},
				});
			});
			
			// Select the sex based on the model value
			$("#sex").val("${model.signupDetails.sex}");
			
		});
	</script>
</head>
<body>

	<h1>New User Signup Form</h1>

	<%
	UserSignup.Model model = (UserSignup.Model) request.getAttribute("model");
	if (model.getErrors().size() > 0) {
		out.append("<div class='errorMessage'>");
		out.append("<ul>");
		for (String error: model.getErrors()) {
			out.append("<li>").append(error).append("</li>");
		}
		out.append("</ul>");
		out.append("</div>");
	}
	%>
	
	<form method="post" action="/signup">
	    <table>
		  <tr>
		    <td>Username:</td><td><input id="username" type="text" name="username" value="${model.signupDetails.username}"/> <span id="usernameMsg"></span><td>
		  <tr>
		  <tr>
		    <td>Password:</td><td><input type="password" name="password" value="${model.signupDetails.password}"/></td>
		  </tr>
		  <tr>
		    <td>Confirm Password:</td><td><input type="password" name="confirmPassword" value="${model.signupDetails.confirmPassword}"/></td>
		  </tr>
		  <tr>
		    <td>Sex:</td>
		    <td>
				<select id="sex" name="sex">
					<option value="Unknown"></option>
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</td>
		   </tr>
		   <tr>
		     <td colspan="2"><input type="checkbox" name="agreedLegal" value="true" /> I agree to the legal terms
		   </tr>
		   <tr>
		     <td colspan="2" style="padding-top: 1em;"><input type="submit"/></td>
		   </tr>
		</table>
	</form>

</body>
</html>