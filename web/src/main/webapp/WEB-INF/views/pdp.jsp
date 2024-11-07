<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <title>Product Details Page</title>

      <shop:css-imports-homepage/>
   </head>
   <body>

      <shop:header/>

      <div class="product-page-main">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="prod-page-title">
                     <h2>${product.productName}</h2>
                  </div>
               </div>
            </div>
            <div class="row">

               <div class="col-md-7 col-sm-8">
                  <div class="md-prod-page">
                     <div class="md-prod-page-in">
                        <div class="page-preview">
                           <div class="preview">
                              <div class="preview-pic tab-content">
                                 <div class="tab-pane active" id="pic-1"><img src="images/product/${product.imgName}" alt="#" /></div>
                              </div>
                           </div>
                        </div>

                     </div>
                     <div class="description-box">
                        <div class="dex-a">
                           <h4>Description</h4>
                           <p>${product.description}</p>
                        </div>

                     </div>
                  </div>

               </div>

               <div class="col-md-5 col-sm-16">
                  <div class="price-box-right">
                     <h4>Price</h4>
                     <h3>$${product.price}</h3>
                     <c:if test="${not empty loggedInUser}">
                     	<a href="checkout?guid=${product.guid}">Buy</a>
                     </c:if>
                     <c:if test="${empty loggedInUser}">
                     	<a href="signin">Buy</a>
                     </c:if>
                     <h5>${orderStatus}</h5>
                     <c:remove var="orderStatus"/>

                  </div>
               </div>

            </div>
         </div>
      </div>


      <style>
         /* Ensure all product images have the same size */
         .preview-pic img {
             width: 550px; /* Set the width */
             height: 450px; /* Set the height */
             object-fit: contain; /* Ensures the entire image fits within the box without cropping */
         }

         /* Make the preview box a fixed size */
         .preview {
             width: 550px; /* Set the box width */
             height: 450px; /* Set the box height */
             overflow: hidden; /* Hide overflow if any */
             display: flex; /* Use flexbox to center the image */
             justify-content: center; /* Center horizontally */
             align-items: center; /* Center vertically */
             background-color: #f8f8f8; /* Optional: Add a background color */
             border: 1px solid #ddd; /* Optional: Add a border for visual appeal */
         }

         /* Increase the font size for various elements */
         .prod-page-title h2 {
             font-size: 28px; /* Increase title font size */
         }

         .description-box h4 {
             font-size: 24px; /* Increase description title font size */
         }

         .price-box-right h4 {
             font-size: 24px; /* Increase price title font size */
         }

         .price-box-right h3 {
             font-size: 28px; /* Increase price font size */
         }

         .price-box-right h5 {
             font-size: 20px; /* Increase order status font size */
         }

         .price-box-right a {
             font-size: 20px; /* Increase buy button font size */
             padding: 10px 15px; /* Add padding to make it button-like */
             color: white;
             background-color: #007bff; /* Button color */
             border-radius: 5px; /* Rounded corners */
             text-decoration: none; /* Remove underline */
         }

         .price-box-right a:hover {
             background-color: #0056b3; /* Darker shade on hover */
         }
      </style>
		
     <shop:footer/>
     <shop:js-imports-homepage/>
   </body>
</html>