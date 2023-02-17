<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Thời khoá biểu</h4>
				</div>
				<div class="clearfix"></div>
			</div>
				<div class="clearfix"></div>
			 
			<!-- <div mbsc-page class="demo-load-events-on-demand">
			  <div style="height:100%">
			      <div id="demo"></div>
			  </div>
			</div> -->
			 <div>
				<div id='calendarTable'></div>
			</div>

            <div class="clearfix"></div>
			<div class="clearfix"></div>
		 
  
	</div>
</div>
   <script src="<c:url value="/resources/js/mobiscroll.jquery.min.js"/>"></script>
         <link href='<c:url value="/resources/css/mobiscroll.jquery.min.css"></c:url>' rel="stylesheet">
     <link href='<c:url value="/resources/calendar/lib/main.css"/>' rel='stylesheet' />
<script src='<c:url value="/resources/calendar/lib/main.js"/>'></script>
     
     
     <script>
     var objectCalendar= ${jsonCalendar};
     console.log(objectCalendar);
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendarTable');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      initialDate: new Date(),//'2020-09-12',
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      selectable: true,
      events: objectCalendar
    });

    calendar.render();
  });

</script>
     
         
         
<script type="text/javascript">
 
 
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/user/list/'/>"+page);
		$("#searchForm").submit();
	}
	function deleteItem(id){
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			location.href="<c:url value='/user/delete/'/>"+id;
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
		$('#calendar').parents("li").addClass('active').siblings().removeClass("active");
		$('#calendar').addClass('current-page').siblings().removeClass("current-page");
		$('#calendar').parents().show();
	});
	

	
</script>



