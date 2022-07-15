<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />
<script>
var emailCnt = 0;
function fn_addEmail(){
	var value = event.currentTarget.value
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if (value.length < 1) { return } 
    if (value.match(regExp)==null) { return alert("올바르지않은 이메일 주소입니다.");} 
	if (value.length > 0) {
		emailCnt++;
		var input = '<input type="hidden" id="email_'+emailCnt+'" name="emailArr" value="'+value+'" />';
		var button = '<button type="button" class="btn btn-outline-danger" onclick="fn_delEmail()" id="'+emailCnt+'">'+value+'</button>' ;
		$("#mailFrm").append(input);
		$("#emailBtnArea").append(button);
		event.currentTarget.value = "";
	}
	
}
function fn_delEmail(){
	var inputData = $('#email_'+ event.currentTarget.id )
	var buttonData= event.currentTarget 	
	
	inputData.remove();
	buttonData.remove();
	
}
function fn_sendMail(){

	document.mailFrm.subject.value = $('#writeSubject').val()
	document.mailFrm.text.value = $('#writeText').val()
	
	var emptyId = null ;
	mailFrm.elements.forEach( function( currentValue , idx, array ) {
		if (emptyId != null) return;
		if( currentValue.value.length<1 ) emptyId = currentValue.id ;
	})
	if(emptyId!=null) return alert("미입력 항목 있습니다. ") //focus ........ 

	
	document.mailFrm.action = '<c:url value="/temp/email/send.do"/>';
	document.mailFrm.submit();
	
}
</script>

<form id="mailFrm" name="mailFrm" method="post" accept-charset="UTF-8">
	<input type="hidden" id="subject" name="subject">
	<input type="hidden" id="text" name="text">  
</form>
<!-- Start Content-->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          	<div class="row">
              <div class="col-lg-12">
              	<label for="exampleFormControlInput1" class="form-label">Email</label>     
<!-- <input type="text" class="form-control" id="writeEmail" name="writeEmail" placeholder="name@example.com" onkeydown="javascript:if (event.keyCode == 13) { fn_addEmail(); }" onblur="javascript: fn_addEmail();"> -->
<!--		엔터 후 포커스를 삭제할 경우  -->
 				<input type="text" class="form-control" id="writeEmail" name="writeEmail" placeholder="name@example.com" onkeydown="javascript:if (event.keyCode == 13) { event.currentTarget.blur();}" onblur="javascript: fn_addEmail();"> 
              </div>
              <div class="col-lg-12" id="emailBtnArea">
              	<!-- <button type="button" class="btn btn-outline-danger"></button> -->
              </div>
            </div>
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>제목</label>
                  <input type="text" class="form-control" required id="writeSubject" name="writeSubject">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-12">
                <div class="form-group">
                  <label><i class="fad fa-check"></i>내용</label>
                  <input type="text" class="form-control" required id="writeText" name="writeText">
                </div>
              </div>
            </div>
            <div class="btn-group bottom-btn float-right">
              <button type="button" class="btn btn-primary" onclick="javascript:fn_sendMail();">발송</button>
            </div>
        </div>
      </div> <!-- end card body-->
    </div> <!-- end card -->
  </div><!-- end col-->
  
<!-- container -->



<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />