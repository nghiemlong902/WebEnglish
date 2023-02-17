<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<header>
         <div id="header-sticky" class="header__area header__transparent header__padding">
            <div class="container-fluid">
               <div class="row align-items-center">
                  <div class="col-xxl-3 col-xl-3 col-lg-4 col-md-2 col-sm-4 col-6">
                     <div class="header__left d-flex">
                        <div class="logo">
                           <a href="<c:url value="/trang-chu" />">
                              <img src="<c:url value="/resources/educal/assets/img/logo/logo.png"/>" alt="logo">
                           </a>
                        </div>
                     </div>
                  </div>
                  <div class="col-xxl-9 col-xl-9 col-lg-8 col-md-10 col-sm-8 col-6">
                     <div class="header__right d-flex justify-content-end align-items-center">
                        <div class="main-menu">
                           <nav id="mobile-menu">
                              <ul>
                                 <li>
                                     <a href="<c:url value="/trang-chu" />" style="color: white">Trang chủ </a>
                                 </li>
                                  <li>
                                     <a href="<c:url value="/danh-sach-khoa-hoc" />" style="color: white">Khoá học </a>
                                 </li>
                              </ul>
                           </nav>
                        </div>
                        <div class="header__search p-relative ml-50 d-none d-md-block">
                           <c:url var="searchCourse"  value="/danh-sach-khoa-hoc/1"/>
            
                            <form:form servletRelativeAction="${searchCourse}" modelAttribute="searchForm" method="post">
                              <form:input path="name" cssClass="form-control" placeholder="Search..."/>
                              <button type="submit"><i class="fad fa-search"></i></button>
                           </form:form>
                       
                        </div>
                        <div class="header__btn ml-20 d-none d-sm-block">
                            <a href="<c:url value="/login"/>" class="e-btn">Đăng nhập</a>
                        </div>
                        <div class="sidebar__menu d-xl-none">
                           <div class="sidebar-toggle-btn ml-30" id="sidebar-toggle">
                               <span class="line"></span>
                               <span class="line"></span>
                               <span class="line"></span>
                           </div>
                       </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </header>