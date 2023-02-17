<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
  
	<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>${title}</h3>
						</div>
					</div>
		<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<form:form servletRelativeAction="/room/save"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
										<form:hidden path="createdDate"/>
										<form:hidden path="updatedDate"/>
										<form:hidden path="dateStr"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên phòng học   <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="name" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Chọn khoá học <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="courseId" cssClass="form-control" disabled="${viewOnly}">
													<form:option value="0">Chọn khoá học </form:option>
													<c:forEach items="${listCourse}" var="item">
														<form:option value="${item.id}">${item.name}</form:option>
													</c:forEach>
												</form:select>											
												 
												<div class="has-error">
													<form:errors path="courseId" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Chọn Giảng viên <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:select path="userId" cssClass="form-control" disabled="${viewOnly}">
													<form:option value="0">Chọn giảng viên dạy </form:option>
													<c:forEach items="${listUser}" var="item">
														<form:option value="${item.id}">${item.name}</form:option>
													</c:forEach>
												</form:select>	
												 <div class="has-error">
													<form:errors path="userId" cssClass="help-block"/>
												</div>
											</div>
										</div>
								 
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Thời gian <span class="required">*</span>
											</label>
											 <div class="col-md-6 col-sm-6 ">
									                <div class='input-group date' id='datetimepicker1'>
											          <input class="form-control dateValue" type="datetime-local"  >
											          <span class="input-group-addon">
											            <span class="glyphicon glyphicon-calendar"></span>
											          </span>
											        </div>						   
									         		<div class="has-error">
									         			<form:errors path="dateStr" cssClass="helpl-block" style="color :red"/>         		
									         		</div>	
								              </div>
					 
											 <div class="col-md-6 col-sm-6 ">
											 	  
											 	</div>
										</div>
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">   </span>
											</label>
											 <div class="col-md-6 col-sm-6 ">
									                <div class='input-group date' id='datetimepicker1'>
											          <input class="form-control dateValue" type="datetime-local"  >
											          <span class="input-group-addon">
											            <span class="glyphicon glyphicon-calendar"></span>
											          </span>
											        </div>						   
									         		<div class="has-error">
									         			<form:errors path="dateStr" cssClass="helpl-block" style="color :red"/>         		
									         		</div>	
								              </div>
					 
											 <div class="col-md-6 col-sm-6 ">
											 	  
											 	</div>
										</div>
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">  </span>
											</label>
											 <div class="col-md-6 col-sm-6 ">
									                <div class='input-group date' id='datetimepicker1'>
											          <input class="form-control dateValue" type="datetime-local"  >
											          <span class="input-group-addon">
											            <span class="glyphicon glyphicon-calendar"></span>
											          </span>
											        </div>						   
									         		<div class="has-error">
									         			<form:errors path="dateStr" cssClass="helpl-block" style="color :red"/>         		
									         		</div>	
								              </div>
					 
											 <div class="col-md-6 col-sm-6 ">
											 	  
											 	</div>
										</div>
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">  </span>
											</label>
											 <div class="col-md-6 col-sm-6 ">
									                <div class='input-group date' id='datetimepicker1'>
											          <input class="form-control dateValue" type="datetime-local" >
											          <span class="input-group-addon">
											            <span class="glyphicon glyphicon-calendar"></span>
											          </span>
											        </div>						   
									         		<div class="has-error">
									         			<form:errors path="dateStr" cssClass="helpl-block" style="color :red"/>         		
									         		</div>	
								              </div>
					 
											 <div class="col-md-6 col-sm-6 ">
											 	  
											 	</div>
										</div>
									<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">  </span>
											</label>
											 <div class="col-md-6 col-sm-6 ">
									                <div class='input-group date' id='datetimepicker1'>
											          <input class="form-control dateValue" type="datetime-local"  >
											          <span class="input-group-addon">
											            <span class="glyphicon glyphicon-calendar"></span>
											          </span>
											        </div>						   
									         		<div class="has-error">
									         			<form:errors path="dateStr" cssClass="helpl-block" style="color :red"/>         		
									         		</div>	
								              </div>
					 
											 <div class="col-md-6 col-sm-6 ">
											 	  
											 	</div>
										</div>
										
							 
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button   onclick="hanlde()" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
													 
												</c:if>			
												<a href='<c:url value="/room/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
												</div>
											</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
		</div>
	</div>
	 
 
	<script type="text/javascript">
	function hanlde(){
		var selectors = $('.dateValue');
		var arrDate = [];
		for(var i= 0 ; i< selectors.length ;i ++){
			if(selectors[i].value != undefined && selectors[i].value.length > 0 ){
				arrDate.push(selectors[i].value);
			}
		}
		var strDate = arrDate.join(',');
		$('#dateStr').val(strDate);
		console.log("submit")
		$("#searchForm").attr('action',"<c:url value='/room/save'/>");
		$("#searchForm").submit();
		console.log("submit")
	 }
 
		$(document).ready(function(){
		  
			
			$('#roomlist').addClass('current-page').siblings().removeClass("current-page");
			$("#roomlist").parents("li").addClass("active").siblings().removeClass("active");
			$("#roomlist").parents().show();			
		});	
	</script>