<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script language='javascript'>
	window.name ="Parent_window";
	
	function fnPopupIpin(){
		window.open('', 'popupIPIN', 'width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
		document.form_ipin.target = "popupIPIN";
		document.form_ipin.action = "https://cert.vno.co.kr/ipin.cb";
		document.form_ipin.submit();
	}
	
	function fnPopupNice(){
		window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		document.form_chk.target = "popupChk";
		document.form_chk.submit();
	}
	
	Kakao.init('b8364a45136a56c0bd9bc08997878971');
	function fn_kakao() {
		  // 로그인 창을 띄웁니다.
		  Kakao.Auth.login({
		    success: function(authObj) {
		        Kakao.API.request({
		            url: '/v2/user/me',
		            success: function(res) {
		            	if(res.kakao_account.email){
		            		document.snsFrm.email.value = res.kakao_account.email; 
		            		document.snsFrm.action = '<c:url value="/kakao_join_callback.do"/>';
		            		document.snsFrm.submit();
		            	}
		            },
		            fail: function(error) {
		               alert(JSON.stringify(error)); 
		            }
		          });
		    },
		    fail: function(err) {
		     alert(JSON.stringify(err)); 
		    }
		  });
	};
</script>

<!-- form 영역 -->
<div style="display:none;" >
	<!-- 아이핀 -->
	<form name="form_ipin" method="post">
		<input type="hidden" id="token_version_id" name="token_version_id" value="${tokenMap.niceDataIpin.token_version_id }" />
      <input type="hidden" id="enc_data" name="enc_data" value="${tokenMap.niceDataIpin.enc_data}" />
      <input type="hidden" id="integrity_value" name="integrity_value" value="${tokenMap.niceDataIpin.integrity_value }"/>
      <input type="hidden" id="param_popup_yn" name="Y" />
	</form>
	
	<!-- 통합인증 -->
	<form name="form_chk" method="post">
	  <input type="hidden" id="m" name="m" value="service"  />
      <input type="hidden" id="token_version_id" name="token_version_id" value="${tokenMap.niceDataChk.token_version_id }" />
      <input type="hidden" id="enc_data" name="enc_data" value="${tokenMap.niceDataChk.enc_data}" />
      <input type="hidden" id="integrity_value" name="integrity_value" value="${tokenMap.niceDataChk.integrity_value }"/>
	</form>
	<!--카카오 -->
	<form method="post" id="snsFrm" name="snsFrm">
		<input type="hidden" name="email">
	</form>
</div>

<div class="sub_wrap">
	<div class="sub_con">
		<div class="commonbox">
			<div class="join">
				<div class="proof phone">
					<h6>본인인증</h6>
					<button class="loginbtn" type="button" onclick="fnPopupNice();">본인인증 <i class="fas fa-long-arrow-right"></i></button>
					<button class="loginbtn" type="button" onclick="fnPopupIpin();">아이핀인증 <i class="fas fa-long-arrow-right"></i></button>
				</div>
				<a href="javascript:void(0);" onclick="javascript:fn_kakao();" title="카카오 로그인 팝업창 열림">카카오 로그인</a>
			</div>
		</div>
	</div>
</div>

<c:import url="/user/inc/footer.do" />