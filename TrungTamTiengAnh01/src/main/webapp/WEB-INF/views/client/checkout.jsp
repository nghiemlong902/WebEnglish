 <%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
          <!-- coupon-area start -->
         <section class="coupon-area pt-100 pb-30">
            <div class="container">
            <div class="row">
               <div class="col-md-6">
                  <div class="coupon-accordion">
                        <!-- ACCORDION START -->
                        <h3> <span id="showlogin"> </span></h3>
                        <div id="checkout-login" class="coupon-content">
                           <div class="coupon-info">
                              <p class="coupon-text"> </p>
                             
                           </div>
                        </div>
                       
                  </div>
               </div>
               <div class="col-md-6">
                  <div class="coupon-accordion">
                        <!-- ACCORDION START -->
                        <h3>  <span id="showcoupon"> </span></h3>
                        <div id="checkout_coupon" class="coupon-checkout-content">
                           <div class="coupon-info">
                              <form action="#">
                                    <p class="checkout-coupon">
                                       <input type="text" placeholder="Coupon Code" />
                                       <button class="e-btn e-btn-border" type="submit"> </button>
                                    </p>
                              </form>
                           </div>
                        </div>
                         
                  </div>
               </div>
            </div>
         </div>
         </section>
         <!-- coupon-area end -->
         <!-- checkout-area start -->
         <section class="checkout-area pb-70">
               <div class="container">
                  <form:form servletRelativeAction="/order-course"   modelAttribute="submitForm" method="POST">
                     <div class="row">
                           <div class="col-lg-6">
                              <div class="checkbox-form">
                                 <h3>Chi tiết hoá đơn </h3>
                                 <div class="row">
                                       <div class="col-md-12">
                                          <div class="checkout-form-list">
                                             <label>Tên</label>
                                             <form:input path="name" type="text" placeholder="Tên" required="required"/>
                                             <form:hidden path="courseId" value="${course.id}"/>
                                              
                                          </div>
                                       </div>
                                       <div class="col-md-6">
                                          <div class="checkout-form-list">
                                             <label>Địa chỉ email  <span class="required">*</span></label>
                                             <form:input path="email" type="email" placeholder="Địa chỉ email" />
                                             <div class="has-error">
													<form:errors path="email" cssClass="help-block"/>
												</div>
                                          </div>
                                       </div>
                                       <div class="col-md-6">
                                          <div class="checkout-form-list">
                                             <label>Số điện thoại  <span class="required">*</span></label>
                                             <form:input  path="phone" type="text" placeholder="Số điện thoại" required="required"/>
                                             
                                          </div>
                                       </div>
                                       <div class="col-md-12">
                                          <div class="checkout-form-list">
                                             <label>Địa chỉ liên hệ  <span class="required">*</span></label>
                                             <form:input path="address" type="text" placeholder="Địa chỉ liên hệ" required="required"/>
                                           
                                          </div>
                                       </div>
              							<div class="col-md-12">
                                          <div class="checkout-form-list text-center">
                                            <button type="submit" class="btn btn-success"> Đặt </button>
                                          </div>
                                       </div>
                                 </div>
 
                              </div>
                           </div>
                           <div class="col-lg-6">
                              <div class="your-order mb-30 ">
                                 <h3>Hoá đơn của bạn </h3>
                                 <div class="your-order-table table-responsive">
                                       <table>
                                          <thead>
                                             <tr>
                                                   <th class="product-name">Tên khoá học</th>
                                                   <th class="product-total">${course.name}</th>
                                             </tr>
                                          </thead>
                                          <tbody>
                                             <tr class="cart_item">
                                                   <td class="product-name">
                                                      Giá tiền  <strong class="product-quantity"> × 1</strong>
                                                   </td>
                                                   <td class="product-total">
                                                      <span class="amount">$${course.price}</span>
                                                   </td>
                                             </tr>
                                             <tr class="cart_item">
                                                   <td class="product-name">
                                                     Mô tả  <strong class="product-quantity"> </strong>
                                                   </td>
                                                   <td class="product-total">
                                                      <span class="amount">${course.description}</span>
                                                   </td>
                                             </tr>
                                          </tbody>
                                        
                                       </table>
                                 </div>
 
                              </div>
                           </div>
                     </div>
                  </form:form>
               </div>
         </section>