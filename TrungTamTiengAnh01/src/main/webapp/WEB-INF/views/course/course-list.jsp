<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Khoá học</h4>
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
									<c:url value="/course/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="name" cssClass="form-control"/>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i> Search</button>												
												<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>												
											</div>
										</div>

									</form:form>
								</div>
							</div>
						</div>
					</div>


	<div class="table-responsive">
		<a href='<c:url value="/course/add"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i>Thêm</button></a>
		 
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title" style="color : black">#</th>
                            <th class="column-title" style="color : black">Id</th>
                            <th class="column-title"style="color : black">Hình ảnh</th>
                            <th class="column-title" style="color : black">Tên</th>  
                            <th class="column-title" style="color : black">Mô tả</th>                        
                            <th class="column-title no-link last text-center" style="color : black" colspan="3" ><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${item.id}</td>             
                            <td><img alt="" width="70px" height="70px" src='<c:url value="${item.imgUrl}"></c:url>'></td>                    
                        	<td>${item.name}</td>
                        	<td>${item.description}</td>
                            <td colspan="3" class="last text-center">
                            	<input type="hidden" id="idProduct" value="${item.id}">
	                            <a href='<c:url value="/course/view/${item.id}"></c:url>' class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"></i></a> 
	                            <a href='<c:url value="/course/edit/${item.id}"></c:url>' class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a> 
	                            <a href="javascript:void(0)" onclick="deleteItem(${item.id})" class="btn btn-danger btn-delete"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>                  
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
     <jsp:include page="/WEB-INF/views/layout/paging.jsp"/>                      


      </div>

		<div id="excel-modal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
				  <form action='<c:url value="/manage/course/import-excel"></c:url>' method="post" enctype="multipart/form-data">
					<div class="modal-header">
						<p class="modal-title">Import sản phẩm</p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body">
						<input type="file" name="file">
					</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" >Có</button>
							<button  class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/course/list/'/>"+page);
		$("#searchForm").submit();
	}
	function deleteItem(id){
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			location.href="<c:url value='/course/delete/'/>"+id;
		}
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
		$('#courselist').parents("li").addClass('active').siblings().removeClass("active");
		$('#courselist').addClass('current-page').siblings().removeClass("current-page");
		$('#courselist').parents().show();
	});
	

	
</script>



