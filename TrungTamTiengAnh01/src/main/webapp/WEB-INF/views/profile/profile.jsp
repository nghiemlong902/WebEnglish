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
									<c:url value="/profile/save" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
 
 										<form:hidden path="roleId"/>
									 <div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align" for="username">Tên tài khoản<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">												
													<form:input path="username" cssClass="form-control" readonly="true"/>
												</div>
											</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="password">Mật khẩu<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:password showPassword="true" path="password" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="password" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="name" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Email<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="email" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="email" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Số điện thoại <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="phone" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="phone" cssClass="help-block"/>
												</div>
											</div>
										</div>
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Địa chỉ <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:textarea path="address" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="address" cssClass="help-block"/>
												</div> 
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Quyền<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
 
											 <c:if test="${submitForm.roleId == 1}">
			                        	 		<input value="Quyền nhân viên" class="form-control" type="text" disabled	/>
			                        	 	</c:if>
			                        	 	<c:if test="${submitForm.roleId == 2}">
			                        	 		<input value="Quyền Giảng viên"  type="text" disabled	/>
			                        	 	</c:if>
			                        	 	<c:if test="${submitForm.roleId == 3}">
			                        	 		<input value="Quyền sinh viên"  type="text" disabled	/>
			                        	 	</c:if>
											</div> 
										</div>
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Giới tính<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">	
												<form:radiobutton path="gender" value="1"  	/> NAM
  												<form:radiobutton path="gender" value="2" 	/> NỮ
  												 <div class="has-error">
													<form:errors path="gender" cssClass="help-block"/>
												</div>
											</div> 
										</div>
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												 								
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
		/* $(document).ready(function(){
			$('#userlist').addClass('current-page').siblings().removeClass("current-page");
			$("#userlist").parents("li").addClass("active").siblings().removeClass("active");
			$("#userlist").parents().show();			
		});	
		 */
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
	</script>