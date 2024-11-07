<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<shop:css-imports-homepage/>
	</head>
   <body>
      <shop:header/>
      <shop:profile-banner/>
      
      <div class="product-profile-box">
         <div class="container">
            <div class="row">
               <div class="col-md-2 col-sm-4 pr">
                  <shop:profile-picture/>
               </div>
               <div class="col-md-10 col-sm-8">
                  
                  <div class="profile-pro-right">
                     <div class="panel with-nav-tabs panel-default">
                        <div class="panel-heading clearfix">
                           <ul class="nav nav-tabs pull-left">
                              <li class="active"><a href="#tab1default" data-toggle="tab">Personal Info</a></li>
                              <li><a href="#tab2default" data-toggle="tab">Referrals</a></li>
                           </ul>
                           
                        </div>
                        
                        
                        <div class="panel-body">
                           <div class="tab-content">
                              <shop:profile-personal-info />
                              <shop:profile-referrals />
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
     
     <shop:footer/>
     <shop:js-imports-homepage/>
   </body>
</html>