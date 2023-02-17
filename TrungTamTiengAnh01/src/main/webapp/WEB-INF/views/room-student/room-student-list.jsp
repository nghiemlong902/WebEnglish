<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Danh sách học sinh trong phòng ${room.name}</h4>
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
									 
								</div>
							</div>
						</div>
					</div>


	<div class="table-responsive">
		<a data-toggle="modal" data-target="#excel-modal"	href="javascript:void(0)" ><button class="btn btn-success" title="Thêm học sinh"><i class="glyphicon glyphicon-plus-sign"></i>Thêm học sinh</button></a>
	 
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title"style="color : black" >#</th>
                            <th class="column-title" style="color : black">Id</th>
                            <th class="column-title" style="color : black">Tên học sinh </th>  
                            <th class="column-title" style="color : black">Điểm</th>  
                                        
                            <th class="column-title no-link last text-center" colspan="3" style="color : black" ><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${item.id}</td>                        
                        	<td>${item.users.name}</td>
                        	<td>${item.point}</td>
                            <td colspan="3" class="last text-center">
                            	 
	                            <a href="javascript:void(0)" onclick="saveId(${item.users.id})" data-toggle="modal" data-target="#point-modal"  class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a> 
	                            <a href="javascript:void(0)" onclick="deleteItem(${item.id})" class="btn btn-danger btn-delete"><i class="glyphicon glyphicon-trash"></i></a>
	                            
	                 
                            </td>                  
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
     <jsp:include page="/WEB-INF/views/layout/paging.jsp"/>                      

	<div id="excel-modal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<p class="modal-title">Thêm sinh viên </p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body" >
						<form action='<c:url value="/room/${room.id}/student/save"></c:url>' method="post" id="form-add-student">
							<div class="item form-group">
											<label class="col-form-label col-md-6 col-sm-6 label-align" for="name">Chọn sinh viên <span class="required">*</span>
											</label>
											<select id="userId" class="form-control" name="userId">
												<c:forEach items="${listUser}" var="item">
														<option value="${item.id}">${item.name}</option>
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
		
			<div id="point-modal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<p class="modal-title">Thêm Điểm  </p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body" >
						<form action='<c:url value="/"></c:url>' method="post" id="form-add-point">
							<div class="item form-group">
											<label class="col-form-label col-md-6 col-sm-6 label-align" for="name">Nhập điểm<span class="required">*</span>
											</label>
											<input class="form-control" type="number" name="point" value = "1" minlength="1"/>
										</div>
						
						</form>
					</div>
						<div class="modal-footer">
							<button onclick="handleAddPoint()"  class="btn btn-success"    >Có</button>
							<button  class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					 
				</div>
			</div>
		</div>
		
		
      </div>
 
	</div>
</div>

<script type="text/javascript">
	var studentId = '';
	function saveId(id){
		studentId = id;
		console.log("1")
	}

	function handleAddPoint(){
		var roomId = '${room.id}';
		$("#form-add-point").attr('action',"<c:url value='/room/"+roomId+"/student/"+studentId+"/add-point'/>");
		$("#form-add-point").submit();
	}

	function handleAddStudent(){
		var roomId = '${room.id}';
		var userId = $('#userId').val();
		 
		$.ajax({
            url: '/api/add-student',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify({
                "roomId": roomId,
                "userId": userId,

            }),
            success: function(result) {
                //do stuff
                console.log("result")
            }
        });
	}
	
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/room/list/'/>"+page);
		$("#searchForm").submit();
	}
	function deleteItem(id){
		var roomId = '${room.id}';
		var url = '/room/'+roomId+'/student/delete/';
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			location.href="<c:url value='/room/"+roomId+"/student/delete/'/>"+id;
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
		$('#roomlist').parents("li").addClass('active').siblings().removeClass("active");
		$('#roomlist').addClass('current-page').siblings().removeClass("current-page");
		$('#roomlist').parents().show();
	});
	

	
</script>



