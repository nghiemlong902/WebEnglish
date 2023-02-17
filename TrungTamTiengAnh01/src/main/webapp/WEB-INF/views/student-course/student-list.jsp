<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Danh sách các khoá học sinh viên</h4>
				</div>
				<div class="clearfix"></div>
			</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									 <form id="searchForm"></form>
								</div>
							</div>
						</div>
					</div>


	<div class="table-responsive">
		<a data-toggle="modal" data-target="#excel-modal"	href="javascript:void(0)" ><button class="btn btn-success" title="Đăng ký khoá học"><i class="glyphicon glyphicon-plus-sign"></i>Đăng ký khoá học</button></a>
	 
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title"style="color : black" >#</th>
                            <th class="column-title" style="color : black">Id</th>
                            <th class="column-title" style="color : black">Tên học sinh </th>  
                            <th class="column-title" style="color : black">Điểm</th>  
                            <th class="column-title" style="color : black">Tên phòng </th>   
                            <th class="column-title" style="color : black">Môn học </th>   
                            <th class="column-title" style="color : black">Thời gian dạy </th>          
                            
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${item.id}</td>                        
                        	<td>${item.users.name}</td>
                        	<td>${item.point}</td>
                        	<td>${item.room.name}</td>
                        	<td>${item.room.courseName}</td>
                        	<td>${item.room.dateStr}</td>
                                     
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
     <jsp:include page="/WEB-INF/views/layout/paging.jsp"/>                      

	<div id="excel-modal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<p class="modal-title">Đăng ký khoá học </p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body" >
						<form action='<c:url value="/student-course/save"></c:url>' method="post" id="form-add-student">
							<div class="item form-group">
											<label class="col-form-label col-md-6 col-sm-6 label-align" for="name">Chọn Phòng Học - Khoá học <span class="required">*</span>
											</label>
											<select id="roomId" class="form-control" name="roomId">
												<c:forEach items="${lstRoom}" var="item">
														<option value="${item.id}">Phòng ${item.name} - Khoá học ${item.courseName}</option>
													</c:forEach>
											</select>
										</div>
						
						</form>
					</div>
						<div class="modal-footer">
							<button type="submit"  class="btn btn-success" form="form-add-student"  >Có</button>
							<button  class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					 
				</div>
			</div>
		</div>
		 
		
		
      </div>
 
	</div>
</div>

<script type="text/javascript">
	 
	
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/student-course/list/'/>"+page);
		$("#searchForm").submit();
	}
 
	
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
	
	$(document).ready(function(){
		$('#studentCourse').parents("li").addClass('active').siblings().removeClass("active");
		$('#studentCourse').addClass('current-page').siblings().removeClass("current-page");
		$('#studentCourse').parents().show();
	});
	

	
</script>



