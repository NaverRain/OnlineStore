<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>404 Page Not Found</title>
  <link rel="stylesheet" href="css/404styles.css">
</head>

<body>

<div class="container">
  <div class="copy-container center-xy">
    <p>
      404, page not found.
    </p>
    <span class="handle"></span>
    <div class="link-container">
		<a href="${pageContext.request.contextPath}/homepage" class="more-link">Visit the original article</a>

    </div>
  </div>
</div>

</body>


</html>
