<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

 <!-- <script src="https://cdn.ckeditor.com/4.18.0/standard/ckeditor.js"></script> -->
 <script src="<c:url value='/resources/ckeditor/ckeditor.js'	/>"></script>
<script src="<c:url value='/resources/ckeditor/adapters/jquery.js'/>"></script>
 
<div class="right_col" role="main">
          <div class="">

            <div class="page-title">
              <div class="title_left">
                <h3> Bài viết </h3>
              </div>
 
            </div>

            <div class="clearfix"></div>
            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                   
                  <div class="x_content">
                    <div class="row">
                      <div class="col-sm-3 mail_list_column">
                        <!-- <button id="compose" class="btn btn-sm btn-success btn-block" type="button">COMPOSE</button> -->
                      <c:forEach items="${listPosts}" var="item" varStatus="i"> 
                        <a href="#">
                          <div class="mail_list" >
                            <div class="left">
                              <i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
                            </div>
                            <div class="right" onclick="findById(${item.id})">
                              <h3>${item.author} <small>${item.date}</small></h3>
                              <p>${item.title}</p>
                            </div>
                          </div>
                        </a>
                 	</c:forEach>
                       
                      </div>
                      <!-- /MAIL LIST -->

                      <!-- CONTENT MAIL -->
                      <div class="col-sm-9 mail_view">
                        <div class="inbox-body">
                          <div class="mail_heading row">
                            <div class="col-md-8">
                             <div class="btn-group">
                       				 <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#modalPost" href="#"><i class="fa fa-reply"></i> Viết bài</button>
                                <!-- <button class="btn btn-sm btn-default" type="button"  data-placement="top" data-toggle="tooltip" data-original-title="Forward"><i class="fa fa-share"></i></button>
                                <button class="btn btn-sm btn-default" type="button" data-placement="top" data-toggle="tooltip" data-original-title="Print"><i class="fa fa-print"></i></button>
                                <button class="btn btn-sm btn-default" type="button" data-placement="top" data-toggle="tooltip" data-original-title="Trash"><i class="fa fa-trash-o"></i></button> -->
                              </div>  
                            </div>
                            <div class="col-md-4 text-right">
                              <p class="date" id="dateStr">  </p>
                            </div>
                            <div class="col-md-12">
                              <h4 id="titleStr">  </h4>
                            </div>
                          </div>
                          <div class="sender-info">
                           
                          </div>
                          <div class="view-mail">
                             <div id="contentStr"></div>
                          </div>
  <div id="modalPost" class="modal fade">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<p class="modal-title">Viết bài </p>
												<button class="close" data-dismiss="modal" >&times;</button>
											</div>
											<div class="modal-body" >
												<form action='<c:url value="/"></c:url>' method="post" id="form-add-point">
													<div class="item form-group">
																	<label class="col-form-label col-md-6 col-sm-6 label-align" for="name">Tiêu đề<span class="required">*</span>
																	</label>
																		<input class="form-control" type="text" name="title" required="required" id="title"/>
																	<label class="col-form-label col-md-6 col-sm-6 label-align" for="name">Nội dung<span class="required">*</span>
																	</label>
																		 
																	<div  class="col-form-label col-md-12 col-sm-12 ">
																		<textarea name="content" id="content"></textarea>
																		<script>
																		   
																		CKEDITOR.replace('content' )
													                	</script>
																	</div>
																</div>
												
												</form>
											</div>
												<div class="modal-footer">
													<button onclick="handleModelPost()"  class="btn btn-success" data-dismiss="modal"    > Đồng ý </button>
													<button  class="btn btn-danger" data-dismiss="modal">Đóng </button>
												</div>
											 
										</div>
									</div>
								</div>
                        </div>

                      </div>
                      <!-- /CONTENT MAIL -->
                      
                      			 
                      
                    </div>
                  </div>
                </div>
              </div>
            </div>
             </div>
            </div>
            
<script type="text/javascript">
 

var url = '<c:url value ="/api/post/create"/>';
 
function handleModelPost(){
	var content = CKEDITOR.instances.content.getData();
	var title =$("#title").val();
	var param = {
			"title": title,
	    	"content": content
			}
	$.ajax({
	    url : url,
	    type : 'POST',
	    data : JSON.stringify(param),
	    contentType: "application/json; charset=utf-8",
        dataType: "json",
	    success : function(data) {              
	        console.log('Data: ',data);
	        alert("success");
	        setTimeout(()=>{
		        location.href="<c:url value='/post'/>";
	        }, 2000);
	    },
	    error : function(request,error){ 
	    	alert(request.responseText);
	       setTimeout(()=>{
		        location.href="<c:url value='/post'/>";
	        }, 2000);
	      
	    }
	});
}

function findById(id){
	var getUrl = '<c:url value ="/api/post/"/>'+id;
	$.ajax({
	    url : getUrl,
	    type : 'GET',
	    contentType: "application/json; charset=utf-8",
        dataType: "json",
	    success : function(data) {              
	        console.log('Data: ',data);
	        $('#dateStr').html(data.created_date);
	        $('#titleStr').html(data.title);
	        $('#contentStr').html(data.content);
	    },
	    error : function(request,error){ 
	    	alert(request.responseText);
	      
	      
	    }
	});
}
	
</script>