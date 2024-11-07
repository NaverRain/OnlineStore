<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="robots" content="noindex, follow">
	
	<shop:css-imports-homepage/>
	<shop:css-imports-signin/>
	

</head>
<body>

		<shop:header/>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
					<c:if test="${UNSUCCESSFUL_LOGIN_COUNT == null || UNSUCCESSFUL_LOGIN_COUNT < 3}">
				<form class="login100-form validate-form" action="signin" method="POST">
				<span class="login100-form-title p-b-55">
					Welcome
				</span>
	
				<div class="wrap-input100 validate-input m-b-16" data-validate="Valid email is required: ex@abc.xyz">
					<input class="input100" type="text" name="email" placeholder="Email">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<span class="lnr lnr-envelope"></span>
					</span>
				</div>
	
				<div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
					<input class="input100" type="password" name="password" placeholder="Password">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<span class="lnr lnr-lock"></span>
					</span>
				</div>

	
				<div class="container-login100-form-btn p-t-25">
					<button class="login100-form-btn" type="submit">
						Login
					</button>
				</div>
	
				<div class="text-center w-full p-t-115">
					<span class="txt1">
						Not a member?
					</span>
	
					<a class="txt1 bo1 hov1" href="signup">
						Sign up now
					</a>
				</div>
				
			</form>
			    </c:if>

            	<c:if test="${UNSUCCESSFUL_LOGIN_COUNT >= 3}">
            		<div>
            			There were 3 unsuccessful attempts to login into the account. Please, try to sign in later
            		</div>
            	</c:if>
		</div>
	</div>
</div>


<shop:footer/>

<shop:js-imports-signin/>
<shop:js-imports-homepage/>

</body>
</html>
