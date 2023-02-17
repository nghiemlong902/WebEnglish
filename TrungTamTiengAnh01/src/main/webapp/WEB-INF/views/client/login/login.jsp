<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<section class="signup__area po-rel-z1 pt-100 pb-145">
            <div class="sign__shape">
               <img class="man-1" src="<c:url value='/resources/educal/assets/img/icon/sign/man-1.png'/>" alt="">
               <img class="man-2" src="<c:url value='/resources/educal/assets/img/icon/sign/man-2.png'/>" alt="">
               <img class="circle" src="<c:url value='/resources/educal/assets/img/icon/sign/circle.png'/>" alt="">
               <img class="zigzag" src="<c:url value='/resources/educal/assets/img/icon/sign/zigzag.png'/>" alt="">
               <img class="dot" src="<c:url value="/resources/educal/assets/img/icon/sign/dot.png"/>" alt="">
               <img class="bg" src="<c:url value="/resources/educal/assets/img/icon/sign/sign-up.png"/>" alt="">
            </div>
            <div class="container">
               <div class="row">
                  <div class="col-xxl-8 offset-xxl-2 col-xl-8 offset-xl-2">
                     <div class="section__title-wrapper text-center mb-55">
                        <h2 class="section__title">Đăng nhập   </h2>
                        
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-xxl-6 offset-xxl-3 col-xl-6 offset-xl-3 col-lg-8 offset-lg-2">
                     <div class="sign__wrapper white-bg">
 
                        <div class="sign__form">
                            <form:form servletRelativeAction="/processLogin" modelAttribute="loginForm" method="POST">
                              <div class="sign__input-wrapper mb-25">
                                 <h5>Tên tài khoản</h5>
                                 <div class="sign__input">
                                    <form:input path="username" cssClass="form-control" placeholder="Tên tài khoản"/>
                                    <div class="has-error">
					         			<form:errors path="username" cssClass="helpl-block" style="color :red"/>         		
					         		</div>	
                                 </div>
                              </div>
                              <div class="sign__input-wrapper mb-10">
                                 <h5>Mật khẩu</h5>
                                 <div class="sign__input">
                                     <form:password path="password" cssClass="form-control" placeholder="Mật khẩu"/>
                                     <div class="has-error">
					         			<form:errors path="password" cssClass="helpl-block" style="color :red"/>         		
					         		</div>
                                    <i class="fal fa-lock"></i>
                                 </div>
                              </div>
 
                              <button class="e-btn  w-100"> <span></span> Đăng nhập</button>
   
                           </form:form>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </section>