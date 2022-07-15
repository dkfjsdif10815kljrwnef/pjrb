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
  	<title>미래도전국방기술 CMS</title>
  	
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
	
	function fn_validatePW(pw){
		var pattern1 = /[0-9]/;
        var pattern2 = /[a-zA-Z]/;
        var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거
        
        if(!pattern1.test(pw)||!pattern2.test(pw)||!pattern3.test(pw)||pw.length < 9||pw.length > 20){
        	 alert("영문+숫자+특수기호 8자리 이상 20자리 이하로 구성하여야 합니다.");
             return false;
        }
        
        var emplyrId = document.frm.id.value;
        if(pw.indexOf(emplyrId) > -1) {
            alert("비밀번호는 ID를 포함할 수 없습니다.");
            return false;
        }
        
        var SamePass_0 = 0; //동일문자 카운트
        var SamePass_1 = 0; //연속성(+) 카운드
        var SamePass_2 = 0; //연속성(-) 카운드
        
        var chr_pass_0;
        var chr_pass_1;
        
        for(var i=0; i < pw.length; i++) {
        	chr_pass_0 = pw.charAt(i);
        	chr_pass_1 = pw.charAt(i+1);
        
         	//동일문자 카운트
         	if(chr_pass_0 == chr_pass_1) {
          		SamePass_0 = SamePass_0 + 1
         	} // if
        
         	//연속성(+) 카운드
	        if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == 1) {
          		SamePass_1 = SamePass_1 + 1
         	} // if
        
         	//연속성(-) 카운드
       		if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == -1) {
        		SamePass_2 = SamePass_2 + 1
        	} // if
        } // for
        
        if(SamePass_0 > 1) {
        	alert("동일문자를 3번 이상 사용할 수 없습니다.");
        	return false;
        } // if
        
        if(SamePass_1 > 1 || SamePass_2 > 1 )  {
        	alert("연속된 문자열(123 또는 321, abc, cba 등)을\n 3자 이상 사용 할 수 없습니다.");
        	return false;
        } // if

        return true;
	}
	
	function pwChange() {
		var f = document.frm;
		
		if ($("#pw0").val() == "") {
			alert("기존 비밀번호를 입력하세요.");
			$("#pw0").focus();
			return false;
		}else if ($("#pw1").val() == "") {
			alert("비밀번호를 입력하세요.");
			$("#pw1").focus();
			return false;
		}else if ($("#pw2").val() == "") {
			alert("비밀번호 확인란을 입력하세요.");
			$("#pw2").focus();
			return false;
		} else if ($("#pw0").val() == $("#pw1").val()) {
			alert("기존 비밀번호와 변경 비밀번호는 동일할 수 없습니다.");
			$("#pw0").focus();
			return false;
		} else if ($("#pw2").val() != $("#pw1").val()) {
			alert("비밀번호와 비밀번호 확인은 동일해야합니다.");
			$("#pw1").focus();
			return false;
		} 
		
		if(fn_validatePW($("#pw1").val())){
			var id = f.id.value;
			var prePw = $("#pw0").val();
			var pw = f.password.value;
			var type = f.type.value;
			$.ajax({
				url : '<c:url value="/cms/pwChange.do"/>',
				data : {emplyrId:id, password:pw, prePw:prePw, type:type},
				dataType : 'json',
				type : 'POST',
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data) {
					if(data.result == 1){
						alert("비밀번호가 변경되었습니다.");
						location.href = "<c:url value='/cms/main.do'/>";
			        }else if(data.result == 0){  	
			        	if(data.returnValue==4){
			        		alert("회원정보가 올바르지 않습니다.");
			        	}
			        	
			    		return false;
			        }
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
              <h1 style="font-weight: 400;">미래도전국방기술 CMS</h1>
            </div>
            <div class="card-body p-4">              
              <form method="post" id="frm" name="frm">
              	<input type="hidden" name="type" value="<c:out value="${loginVO.type }"/>">
                <div class="form-group">
                  <label for="id">아이디</label>
                  <input type="text" id="id" class="form-control" placeholder="" required="" autocomplete="off" name="id" value="<c:out value="${loginVO.emplyrId }"/>" readonly>
                </div>
                <div class="form-group">
                  <label for="password">기존 비밀번호</label>
                  <div class="input-group input-group-merge">
                    <input type="password" id="pw0" class="form-control" placeholder="" required="" autocomplete="off">
                    <div class="input-group-append" data-password="false">
                      <div class="input-group-text">
                        <span class="password-eye"></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label for="password">비밀번호</label>
                  <div class="input-group input-group-merge">
                    <input type="password" id="pw1" class="form-control" placeholder="" required="" autocomplete="off" name="password">
                    <div class="input-group-append" data-password="false">
                      <div class="input-group-text">
                        <span class="password-eye"></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label for="password">비밀번호 확인</label>
                  <div class="input-group input-group-merge">
                    <input type="password" id="pw2" class="form-control" placeholder="" required="" autocomplete="off">
                    <div class="input-group-append" data-password="false">
                      <div class="input-group-text">
                        <span class="password-eye"></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-group mb-0 text-center">
                  <button type="button" class="btn btn-block btn-xs btn-primary" onclick="javascript:pwChange()">비밀번호 변경하기</button>
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

