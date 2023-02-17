<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
  
         
                  <!-- course area start -->
         <section class="course__area pt-115 pb-120 grey-bg">
            <div class="container">
               <div class="row align-items-end">
                  <div class="col-xxl-5 col-xl-6 col-lg-6">
                     <div class="section__title-wrapper mb-60">
                        <h2 class="section__title">Tìm đúng 
 							<br> Khóa học <span class="yellow-bg yellow-bg-big">trực tuyến <img src="<c:url value="/resources/educal/assets/img/shape/yellow-bg.png"/>" alt=""></span> dành cho bạn</h2>
                        <p>Bạn không cần phải vật lộn một mình, bạn đã có sự hỗ trợ và giúp đỡ của chúng tôi.</p>
                     </div>
                  </div>
              
               </div>
               <div class="row grid">
               <c:forEach items="${courses}" var="item"  >  
                  <div class="col-xxl-4 col-xl-4 col-lg-4 col-md-6 grid-item cat1 cat2 cat4">
                     <div class="course__item white-bg mb-30 fix">
                        <div class="course__thumb w-img p-relative fix">
                           <a href="#">
                              <img  src='<c:url value="${item.imgUrl}"></c:url>' height="215px">
                           </a>
                        </div>
                        <div class="course__content">
                            <div class="course__meta d-flex align-items-center justify-content-between">
                                            <!--  <div class="course__lesson">
                                                <span><i class="far fa-book-alt"></i>72 Lesson</span>
                                             </div> -->
                                             <div class="course__rating">
                                                <span><i class="icon_star"></i>4.5 (44)</span>
                                             </div>
                                          </div>
                           <h3 class="course__title"><a href="#">${item.name}.</a></h3>
                        </div>
                        <div class="course__more d-flex justify-content-between align-items-center">
                           <div class="course__status">
                              <span><fmt:formatNumber type = "number" 
         maxFractionDigits = "3" value = "${item.price}" /> VNĐ</span>
                           </div>
                           <div class="course__btn">
                              <a href="<c:url value="/dat-hang/${item.id}"/>" class="link-btn">
                                 Liên hệ
                                 <i class="far fa-arrow-right"></i>
                                 <i class="far fa-arrow-right"></i>
                              </a>
                           </div>
                        </div>
                     </div>
                  </div>
                  </c:forEach>
                 
               </div>
            </div>
         </section>