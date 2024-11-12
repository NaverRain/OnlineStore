<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:url var="css" value = "/css/404styles.css"/>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin panel</title>
  <link rel="stylesheet" href="${css}">
</head>

<body>

<div class="container">
  <div class="copy-container center-xy">
    <p>
      Secret plane for admin users.
    </p>
    <span class="handle"></span>
    <div class="link-container">
		<a href="${pageContext.request.contextPath}/homepage" class="more-link">Visit the original article</a>

    </div>
  </div>
</div>

</body>


</html>
