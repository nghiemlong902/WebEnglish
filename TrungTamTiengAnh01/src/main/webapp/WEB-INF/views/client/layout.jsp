<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
 

<!doctype html>
<html class="no-js" lang="zxx">
   
<head>
      <meta charset="utf-8">
      <meta http-equiv="x-ua-compatible" content="ie=edge">
      <title>Khoá học  </title>
      <meta name="description" content="">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Place favicon.ico in the root directory -->
      <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/educal/assets/img/favicon.png"/>">
      <!-- CSS here -->
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/preloader.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/bootstrap.min.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/meanmenu.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/animate.min.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/owl.carousel.min.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/swiper-bundle.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/backToTop.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/jquery.fancybox.min.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/fontAwesome5Pro.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/elegantFont.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/default.css'	/>">
      <link rel="stylesheet" href="<c:url value='/resources/educal/assets/css/style.css'	/>">
   </head>
   <body>
      <!--[if lte IE 9]>
      <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
      <![endif]-->
      
      <!-- Add your site or application content here -->  

      <!-- pre loader area start -->
      <div id="loading">
         <div id="loading-center">
            <div id="loading-center-absolute">
               <div class="loading-content">
                  <img class="loading-logo-text" src="<c:url value='/resources/educal/assets/img/logo/logo-text-2.png'/>" alt="">
                  <div class="loading-stroke">
                     <img class="loading-logo-icon" src="<c:url value='/resources/educal/assets/img/logo/logo-icon.png'/>" alt="">
                  </div>
               </div>
            </div>
         </div>  
      </div>
      <!-- pre loader area end -->

      <!-- back to top start -->
      <div class="progress-wrap">
         <svg class="progress-circle svg-content" width="100%" height="100%" viewBox="-1 -1 102 102">
            <path d="M50,1 a49,49 0 0,1 0,98 a49,49 0 0,1 0,-98" />
         </svg>
      </div>
      <!-- back to top end -->

      <!-- header area start -->
       <tiles:insertAttribute name="header" />	
      <!-- header area end -->
 
      <div class="body-overlay"></div>
      <!-- cart mini area end -->


      <!-- sidebar area start -->
      <div class="sidebar__area">
         <div class="sidebar__wrapper">
            <div class="sidebar__close">
               <button class="sidebar__close-btn" id="sidebar__close-btn">
               <span><i class="fal fa-times"></i></span>
               <span>close</span>
               </button>
            </div>
            <div class="sidebar__content">
               <div class="logo mb-40">
                  <a href="<c:url value="/trang-chu"	/>">
                  <img src="<c:url value="/resources/educal/assets/img/logo/logo.png"/>" alt="logo">
                  </a>
               </div>
               <div class="mobile-menu fix"></div>

               <div class="sidebar__search p-relative mt-40 ">
                  <form action="#">
                     <input type="text" placeholder="Search...">
                     <button type="submit"><i class="fad fa-search"></i></button>
                  </form>
               </div>
  
            </div>
         </div>
      </div>
      <!-- sidebar area end -->      
      <div class="body-overlay"></div>
      <!-- sidebar area end -->

      <main>

         <!-- hero area start -->
 		<tiles:insertAttribute name="banner" />			
         <!-- hero area end -->
 
 
		<tiles:insertAttribute name="body" />	
 
         <!-- course area end -->
   
      </main>

         <!-- footer area start -->
       <tiles:insertAttribute name="footer" />			
         <!-- footer area end -->
         <script>
         	
	$(document).ready(function(){
		var msgError = '${msgError}';
		var msgSuccess ='${msgSuccess}';
		if(msgError){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgError,
		        type: 'error',
		        styling: 'bootstrap3'
		        
		    });	
		}
		if(msgSuccess){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgSuccess,
		        type: 'success',
		        styling: 'bootstrap3'
		    });	
		}
	});
         </script>
      <!-- JS here -->
	<script src="<c:url value='/resources/educal/assets/js/vendor/jquery-3.5.1.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/vendor/waypoints.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/bootstrap.bundle.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/jquery.meanmenu.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/swiper-bundle.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/owl.carousel.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/jquery.fancybox.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/isotope.pkgd.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/parallax.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/backToTop.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/jquery.counterup.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/ajax-form.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/wow.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/imagesloaded.pkgd.min.js'/>"></script>
      <script src="<c:url value='/resources/educal/assets/js/main.js'/>"></script>
   </body>

</html>

