<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
									<form:form servletRelativeAction="/course/save" enctype="multipart/form-data"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
										<form:hidden path="createdDate"/>
										<form:hidden path="updatedDate"/>
										 <form:hidden path="imgUrl"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên khoá học  <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="name" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"/>
												</div>
											</div>
										</div>
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Giá tiền <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">				
												<form:input path="price" cssClass="form-control" readonly="${viewOnly}" maxlength="8"  type="number" min="1"  />
												<div class="has-error">
													<form:errors path="price" cssClass="help-block"/>
												</div>
											</div>
										</div>
										 <c:if test="${!viewOnly}">
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Hình ảnh<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">												
													<form:input type="file" path="imgFile" cssClass="form-control" readonly="${viewOnly}"/>
													<div class="has-error">
														<form:errors path="imgFile" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</c:if> 
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Mô tả<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:textarea path="description" cssClass="form-control" readonly="${viewOnly}"/>
												
											</div>
										</div>
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/course/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
		$(document).ready(function(){
			$('#courselist').addClass('current-page').siblings().removeClass("current-page");
			$("#courselist").parents("li").addClass("active").siblings().removeClass("active");
			$("#courselist").parents().show();			
		});	
	</script>