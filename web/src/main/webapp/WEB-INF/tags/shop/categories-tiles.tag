<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-content-product">
	<div class="main-product">
		<div class="container">
			<div class="row clearfix">
				<c:forEach items="${categories}" var="category">
					<div class="col-lg-3 col-sm-6 col-md-3">
						<a href="category?id=${category.id}&page=1">
							<div class="box-img">
								<h4>${category.categoryName}</h4>
								<img src="images/category/${category.imgName}" alt="" />
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<style>
.page-content-product a {
    font-size: 18px;
    padding: 5px 10px;
    text-decoration: none;
    color: #333;
}

.page-content-product a.active {
    font-weight: bold;
    color: #007bff;
}

/* New styles for larger images */
.page-content-product .box-img {
    text-align: center; /* Center-align the text and image */
}

.page-content-product img {
    width: 550px; /* Fixed width */
    height: 450px; /* Fixed height */
    object-fit: contain; /* Ensures the entire image fits without cropping */
    margin-bottom: 10px; /* Space below the image */
}
</style>