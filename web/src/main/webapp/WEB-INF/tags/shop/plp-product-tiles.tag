<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>


<div class="col-md-6 col-sm-8">
	<div class="furniture-middle">

		<c:forEach items="${products}" var="product">
			<div class="big-box">
				<div class="big-img-box">
					<img src="images/product/${product.imgName}" alt="#" />
				</div>
				<div class="big-dit-b clearfix">
					<div class="col-md-6">
						<div class="left-big">
							<h3>${product.productName}</h3>
						</div>
					</div>
					<div class="col-md-6">
						<div class="right-big-b">
							<div class="tight-btn-b clearfix">
								<a class="view-btn" href="product?guid=${product.guid}">View</a> <a
									href="product?guid=${product.guid}">$${product.price}</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<shop:pagination/>
		
	</div>
</div>

<style>
/* Adjust product name size */
.furniture-middle h3 {
    font-size: 24px; /* Increase product name size */
    font-weight: bold;
    margin: 0;
    color: #333;
}

/* Make the image box larger */
.big-img-box {
    width: 550px; /* Set a fixed width for the image box */
    height: 450px; /* Set a fixed height for the image box */
    overflow: hidden; /* Hide overflow to prevent distortion */
    display: flex; /* Flexbox to center the image */
    align-items: center; /* Center vertically */
    justify-content: center; /* Center horizontally */
    margin-bottom: 10px; /* Space below the image box */
}

/* Style for the images */
.big-img-box img {
    width: 100%; /* Make the image responsive to the box */
    height: auto; /* Maintain aspect ratio */
    object-fit: contain; /* Ensures the entire image fits within the box */
}

/* Style for the buttons with View and Price */
.tight-btn-b a {
    font-size: 20px; /* Increase font size */
    padding: 10px 15px; /* Add padding to make it button-like */
    text-decoration: none;
    color: white;
    background-color: #007bff; /* Button color */
    border-radius: 5px; /* Rounded corners */
    display: inline-block;
    text-align: center;
    margin-right: 10px; /* Space between buttons */
    font-weight: bold;
}

/* Hover effect */
.tight-btn-b a:hover {
    background-color: #0056b3; /* Darker shade on hover */
}

/* Price specific styling */
.tight-btn-b a[href*="product"] {
    font-size: 20px; /* Increase font size */
    padding: 10px 15px; /* Add padding to make it button-like */
    text-decoration: none;
    color: white;
    background-color: #007bff; /* Button color */
    border-radius: 5px; /* Rounded corners */
    display: inline-block;
    text-align: center;
    margin-right: 10px; /* Space between buttons */
    font-weight: bold;
}
</style>