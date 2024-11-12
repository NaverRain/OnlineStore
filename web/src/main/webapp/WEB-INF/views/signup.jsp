<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Sign Up</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="robots" content="noindex, follow">
	
	<shop:css-imports-homepage/>
	<shop:css-imports-signin/>
    <style>
    .error {
        color:red
    }
    </style>

</head>
<body>

	<shop:header/>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
				<form:form class="login100-form validate-form" action="signup" modelAttribute="user" method="POST">
					<span class="login100-form-title p-b-55">
						Sign Up
					</span>

<!-- First Name Field -->
<div class="wrap-input100 validate-input m-b-16" data-validate="Enter First Name">
    <form:input class="input100" path="firstName" placeholder="First Name"/>
    <span class="focus-input100"></span>
    <span class="symbol-input100">
        <span class="lnr lnr-user"></span>
    </span>
    <form:errors path="firstName" cssClass="error"/>
</div>

<!-- Last Name Field -->
<div class="wrap-input100 validate-input m-b-16" data-validate="Enter Last Name">
    <form:input class="input100" path="lastName" placeholder="Last Name"/>
    <span class="focus-input100"></span>
    <span class="symbol-input100">
        <span class="lnr lnr-user"></span>
    </span>
    <form:errors path="lastName" cssClass="error"/>
</div>

<!-- Email Field -->
<div class="wrap-input100 validate-input m-b-16" data-validate="Valid email is required: ex@abc.xyz">
    <form:input class="input100" path="email" placeholder="Email"/>
    <span class="focus-input100"></span>
    <span class="symbol-input100">
        <span class="lnr lnr-envelope"></span>
    </span>
    <form:errors path="email" cssClass="error"/>
</div>

<!-- Password Field -->
<div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
    <form:password  class="input100" path="password" placeholder="Password"/>
    <span class="focus-input100"></span>
    <span class="symbol-input100">
        <span class="lnr lnr-lock"></span>
    </span>
    <form:errors path="password" cssClass="error"/>
</div>

<!-- Repeat Password Field -->
<div class="wrap-input100 validate-input m-b-16" data-validate="Repeat password is required">
    <form:password  class="input100" path="repeatPassword" placeholder="Repeat Password"/>
    <span class="focus-input100"></span>
    <span class="symbol-input100">
        <span class="lnr lnr-lock"></span>
    </span>
    <form:errors path="repeatPassword" cssClass="error"/>
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
						Have account?
					</span>
	
					<a class="txt1 bo1 hov1" href="signin">
						Sign in now
					</a>
				</div>
				</form:form>
			</div>
		</div>
	</div>

	<shop:footer/>

	<shop:js-imports-signin/>
	<shop:js-imports-homepage/>
</body>
</html>
