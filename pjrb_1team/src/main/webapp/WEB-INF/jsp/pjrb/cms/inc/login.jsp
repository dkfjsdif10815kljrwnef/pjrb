<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="ko">
<head>
	<meta charset="UTF-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  	<meta name="viewport" content="width=device-width, initial-scale=1" />
  	<title>DEFAULT CMS</title>
  	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/all.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/webfonts.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/common.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/light.min.css" id="light-style" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/dark.min.css" id="dark-style" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/layout.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/board.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/ckeditor.css" />
    
    <script src="${pageContext.request.contextPath}/js/cms/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cms/vendor.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/cms/common.js"></script>
	
	<script>
	// 헤더
	$( document ).ready( function () {
		var cmsInputId = getCookie("cmsInputId");
		$("input[name='emplyrId']").val(cmsInputId); 
		
		if($("input[name='emplyrId']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#id_save").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#id_save").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#id_save").is(":checked")){ // ID 저장하기 체크했을 때,
	            var cmsInputId = $("input[name='emplyrId']").val();
	            setCookie("cmsInputId", cmsInputId, 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("cmsInputId");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("input[name='emplyrId']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#id_save").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            var cmsInputId = $("input[name='emplyrId']").val();
	            setCookie("cmsInputId", cmsInputId, 7); // 7일 동안 쿠키 보관
	        }
	    });
	    
	    returnInit();
	} );	
	
	function returnInit(){
		if('${returnMsg}' == 2){
			/* alert("로그인이 필요합니다."); */
		}else if('${returnMsg}' == 4){
			alert("비밀번호가 올바르지 않습니다. 관리자에게 문의하세요.");
		}else if('${returnMsg}' == 5){
			alert("일치하는 아이디가 없습니다. 관리자에게 문의하세요.");
		}else if('${returnMsg}' == 6){
			alert("해당 아이디가 미사용 중입니다. 관리자에게 문의하세요.");
		}
	}
	
	
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}
	
	function actionLogin() {
		var f = document.frm;
		
		if (f.emplyrId.value == "") {
			alert("아이디를 입력하세요.");
			f.emplyrId.focus();
			return false;
		} else if (f.password.value == "") {
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		} else {
			var emplyrId = f.emplyrId.value;
			var pw = f.password.value;
			$.ajax({
				url : '<c:url value="/dupleLoginCheck.do"/>',
				data : {emplyrId:emplyrId, password:pw},
				dataType : 'json',
				type : 'POST',
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				async:false,
				success : function(data) {
					
					if(data.result == 0){  	
			        	if(data.returnValue==4){
			        		alert("회원정보가 올바르지 않습니다.");
			        	}else if(data.returnValue==5){
			        		alert("비밀번호 틀린 횟수가 5회 이상입니다. 관리자에게 문의하세요.");
			        	}
			        	
			    		return false;
			        } 
					if(data.accessDeny == "accessDeny"){
						alert("접근할수 없습니다.")
						return;
					}
					if(data.dupleLoginInfo == "Y"){
						if(!confirm("먼저 로그인되어 있는 다른 브라우저의 ID를 로그아웃 후 로그인 하시겠습니까?")){
							return;
						}
					}
					$.ajax({
						url : '<c:url value="/cms/actionLogin.do"/>',
						data :{emplyrId:emplyrId, password:pw,dupleLoginInfo:data.dupleLoginInfo},
						dataType : 'json',
						type : 'POST',
						contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
						async:false,
						success : function(data) {
							 if(data.result == 1){
								if(data.returnValue==6){
					        		alert("비밀번호 변경일이 90일이 지났습니다. 비밀번호를 변경해주세요.");
					        	}
								location.href = "<c:url value='/cms/main.do'/>";
					        }
						}
					})
						
					
					
					
				}
			});
		}
	}
	</script>
</head>
<body class="authentication-bg" data-layout-config='{"leftSideBarTheme":"default","layoutBoxed":false, "leftSidebarCondensed":false, "leftSidebarScrollable":false,"darkMode":true, "showRightSidebarOnStart": false}' data-leftbar-theme="dark" data-editor="DecoupledDocumentEditor" data-collaboration="false">
  <div class="account-pages mt-5 mb-5">
    <div class="container">
      <div class="row justify-content-center login">
        <div class="col-lg-5">
          <div class="card">
            <!-- Logo -->
            <div class="card-header pt-4 pb-4 text-center">
              <h1 style="font-weight: 400;">DEFAULT CMS</h1>
            </div>
            <div class="card-body p-4">              
              <form method="post" id="frm" name="frm">
              	<input type="hidden" name="type" value="">
                <div class="form-group">
                  <label for="id">아이디</label>
                  <input type="text" id="emplyrId" class="form-control" placeholder="" required="" autocomplete="off" name="emplyrId" onkeydown="javascript:if (event.keyCode == 13) { actionLogin();}">
                </div>
                <div class="form-group">
                  <label for="password">비밀번호</label>
                  <div class="input-group input-group-merge">
                    <input type="password" id="password" class="form-control" placeholder="" required="" autocomplete="off" name="password"onkeydown="javascript:if (event.keyCode == 13) { actionLogin();}">
                    <div class="input-group-append" data-password="false">
                      <div class="input-group-text">
                        <span class="password-eye"></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-group mb-3">
                  <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="id_save" checked="">
                    <label class="custom-control-label" for="id_save"><span>아이디 저장</span></label>
                  </div>
                </div>
                <div class="form-group mb-0 text-center">
                  <button type="button" class="btn btn-block btn-xs btn-primary" onclick="javascript:actionLogin()">로그인</button>
                </div>
              </form>
            </div> <!-- end card-body -->
          </div>
          <!-- end card -->
        </div> <!-- end col -->
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  </div>
  <!-- end page -->
</body>

</html>

