  <%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>   
 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
         <!-- course area start -->
         <section class="course__area pt-120 pb-120">
            <div class="container">
 
               <div class="row">
                  <div class="col-xxl-12">
                     <div class="course__tab-conent">
                        <div class="tab-content" id="courseTabContent">
                           <div class="tab-pane fade show active" id="grid" role="tabpanel" aria-labelledby="grid-tab">
                              <div class="row">
                   <c:forEach items="${courses}" var="item"  >  
                  <div class="col-xxl-4 col-xl-4 col-lg-4 col-md-6 grid-item cat1 cat2 cat4">
                     <div class="course__item white-bg mb-30 fix">
                        <div class="course__thumb w-img p-relative fix">
                           <a href="#">
                              <img  height="215px" src='<c:url value="${item.imgUrl}"   ></c:url>'>
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
                  </div>
               </div>
               <div class="row">
                  <div class="col-xxl-12">
                     <div class="basic-pagination wow fadeInUp mt-30" data-wow-delay=".2s">
                        <ul class="d-flex align-items-center"> 
                    
                             <c:if test="${pageInfo.indexPage - 1 == 0}"> 
                             <li class="next">
                              <a onclick="gotoPage(${pageInfo.indexPage})" class="link-btn">
                                 Prev
                                 <i class="arrow_left"></i>
                                 <i class="arrow_left"></i>
                              </a>
                           </li>
                           </c:if>
                            <c:if test="${pageInfo.indexPage -1 >= 1}"> 
                             <li class="next">
                              <a onclick="gotoPage(${pageInfo.indexPage - 1})" class="link-btn">
                                 Prev
                                 <i class="arrow_left"></i>
                                 <i class="arrow_left"></i>
                              </a>
                           </li>
                           </c:if>
                          <c:forEach begin="1" end="${pageInfo.totalPage}" varStatus="i">
		              		<c:choose>
		              			<c:when test="${pageInfo.indexPage == i.index}">
		              				<li class="active"><a href="javascript:void(0)" >${pageInfo.indexPage}</a></li>
		              			</c:when>
		              			<c:otherwise>
		              				 <li>
		                              <a onclick="gotoPage(${i.index})">
		                                 <span>${i.index}</span>
		                              </a>
		                           </li>
		              			</c:otherwise>
		              		</c:choose>
		              	</c:forEach>
                          
                           <c:if test="${pageInfo.indexPage + 1 > pageInfo.totalPage}"> 
                             <li class="next">
                              <a onclick="gotoPage(${pageInfo.indexPage})" class="link-btn">
                                 Next
                                 <i class="arrow_right"></i>
                                 <i class="arrow_right"></i>
                              </a>
                           </li>
                           </c:if>
                            <c:if test="${pageInfo.indexPage + 1 <= pageInfo.totalPage}"> 
                             <li class="next">
                              <a onclick="gotoPage(${pageInfo.indexPage + 1})" class="link-btn">
                                 Next
                                 <i class="arrow_right"></i>
                                 <i class="arrow_right"></i>
                              </a>
                           </li>
                           </c:if>
                        </ul>
                     </div>
                  </div>
               </div>
            </div>
         </section>
         <!-- course area end -->
 
<script>
function gotoPage(page){
	$("#searchForm").attr('action',"<c:url value='/danh-sach-khoa-hoc/'/>"+page);
	$("#searchForm").submit();
}

</script>