<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Edit Profile</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<shop:css-imports-homepage/>
	<shop:css-imports-signin/>
</head>

    <body>
        <shop:header/>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
				<form class="login100-form validate-form" action="edit-profile" method="POST">
					<span class="login100-form-title p-b-55">
						Update Profile
					</span>

					<!-- First Name Field -->
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Enter First Name">
						<input class="input100" type="text" name="firstName" placeholder="First Name" value="${loggedInUser.firstName}">
						<span class="focus-input100"></span>
							<span class="symbol-input100">
							<span class="lnr lnr-user"></span>
						</span>
					</div>

					<!-- Last Name Field -->
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Enter Last Name">
						<input class="input100" type="text" name="lastName" placeholder="Last Name" value="${loggedInUser.lastName}">
						<span class="focus-input100"></span>
							<span class="symbol-input100">
							<span class="lnr lnr-user"></span>
						</span>
					</div>

					<!-- Email Field -->
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Valid email is required: ex@abc.xyz">
						<input class="input100" type="text" name="email" placeholder="Email" value="${loggedInUser.email}">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<span class="lnr lnr-envelope"></span>
						</span>
					</div>

					<!-- Password Field -->
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Enter old password">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<span class="lnr lnr-lock"></span>
						</span>
					</div>

					<!-- Repeat Password Field -->
					<div class="wrap-input100 validate-input m-b-16" data-validate="Repeat password is required">
						<input class="input100" type="password" name="newPassword" placeholder="Enter a new password">
						<span class="focus-input100"></span>
							<span class="symbol-input100">
							<span class="lnr lnr-lock"></span>
						</span>
					</div>

					<%-- Display error message if exists --%>
					<c:if test="${errMsg != null}">
						<div class="container-login100-form-btn">
							<span class="txtErr">${errMsg}</span>
						</div>
					</c:if>
					<c:remove var="errMsg"/>

					<!-- Signup Button -->
					<div class="container-login100-form-btn p-t-25" >
						<button class="login100-form-btn">
							Sign Up
						</button>
					</div>

					<div class="text-center w-full p-t-115">
					<span class="txt1">
						Don't want to edit profile?
					</span>

					<a class="txt1 bo1 hov1" href="profile">
						Back to profile
					</a>
				</div>
				</form>
			</div>
		</div>
	</div>




        <shop:footer/>

        <shop:js-imports-homepage/>
        <shop:js-imports-signin/>
    </body>
</html>