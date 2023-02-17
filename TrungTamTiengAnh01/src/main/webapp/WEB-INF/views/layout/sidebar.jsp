<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<h3>General</h3>
			<ul class="nav side-menu" id="home">
				<li><a href='<c:url value="/index"></c:url>'><i class="fa fa-home"></i>Trang chủ  </a>					
				</li>
				
			</ul>
		  <c:if test="${userInfo.roleId == 3}">  
				<ul class="nav side-menu" id="inventory">
					<li><a><i class="	fa fa-industry"></i>Khoá học<span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="studentCourse"><a href='<c:url value="/student-course/list"></c:url>'>Quản lý khoá học </a></li>
						</ul>
					</li>
				</ul>
			</c:if> 
			 <c:if test="${userInfo.roleId == 2 || userInfo.roleId == 3}">  
				<ul class="nav side-menu" id="maintenance">
					<li><a><i class="fa fa-tasks"></i>Lịch <span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="calendar"><a href='<c:url value="/calendar"></c:url>'> Thời khoá biểu</a></li>				
						</ul>
					</li>
				</ul>
			</c:if>
			<ul class="nav side-menu" id="maintenance">
					<li><a><i class="fa fa-tasks"></i>Bài viết <span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="calendar"><a href='<c:url value="/post"></c:url>'>Bài viết </a></li>				
						</ul>
					</li>
			</ul>
			  <c:if test="${userInfo.roleId == 1  || userInfo.roleId == 2}">  
				<ul class="nav side-menu" id="manage">
					<li><a><i class="fa fa-users"></i>Quản lý<span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
						 <c:if test="${userInfo.roleId == 1 }">  
							<li id="courselist"><a href='<c:url value="/course/list"></c:url>'>Khoá học</a></li>
							<li id="userlist"><a href='<c:url value="/user/list"></c:url>'>Tài khoản</a></li>	
							<li id="orderList"><a href='<c:url value="/order-course/list/1"></c:url>'>Đơn đăng ký</a></li>
							</c:if>  
							<li id="roomlist"><a href='<c:url value="/room/list"></c:url>'>Phòng</a></li>			
						</ul>
					</li>
				</ul>
 				</c:if>   
			 <c:if test="${userInfo.roleId == 1 }">     
			<ul class="nav side-menu" id="statistics">
					<li><a><i class="fa fa-bar-chart" aria-hidden="true"></i>Báo cáo<span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="chart"><a href='<c:url value="/statistics/chart"></c:url>'>Chart</a></li>												
						</ul>
					</li>
			</ul>
		  </c:if>  
	</div>
</div>