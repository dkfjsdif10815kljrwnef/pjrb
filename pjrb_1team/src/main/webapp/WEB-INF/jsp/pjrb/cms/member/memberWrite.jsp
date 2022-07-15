<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
	$(document).ready(function() {
		fn_typeChange();
		
		if ("${result.emplyrId}" != "") {
			document.frm.act.value = 'update';
		}
	});

	function fn_memberList() {
		document.frm.action = '<c:url value="/cms/member/memberList.do"/>';
		document.frm.submit();
	}

	function fn_submit() {
		var emplyrId = document.frm.emplyrId.value;
		var type = document.frm.type.value;
		
		if (emplyrId == "") {
			document.frm.idChk.value = '0';

			alert("아이디를 입력하세요.");
			return false;
		}
		
		if (document.frm.userNm.value == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		
		if (document.frm.act.value == 'write' || (document.frm.act.value == 'update' && $("input:checkbox[name=pwChange]").is(":checked") == true)) {
			if (document.frm.password.value == "" || $("#pw2").val()  == "") {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			if($("#pw1").val()!=$("#pw2").val()){
				alert("비밀번호와 비밀번호확인란은 동일하여야 합니다.");
				return false;
			}
			
			if(!fn_validatePW(document.frm.password.value)){
				return false;
			}
		}
		
		var url = '<c:url value="/cms/member/memberInsert.do"/>';

		if (document.frm.act.value == 'update') {
			url = '<c:url value="/cms/member/memberUpdate.do"/>';
		}

		var status = "N";
		if ($("input:checkbox[id=switch1]").is(":checked") == true) {
			status = "Y";
		}
		document.frm.status.value = status;
		
		if(type=='general' || type=='orgnzt'){
			var deleteAt = "N";
			if ($("input:checkbox[id=switch2]").is(":checked") == true) {
				deleteAt = "Y";
			}
			document.frm.deleteAt.value = deleteAt;
		}
		
		if(type=='orgnzt'){
			var allowYn = "N";
			if ($("input:checkbox[id=switch3]").is(":checked") == true) {
				allowYn = "Y";
			}
			document.frm.allowYn.value = allowYn;
		}

		var idChk = 1;
		if (document.frm.act.value == 'write') {
			$.ajax({
				url : '<c:url value="/cms/member/memberIdChk.do"/>',
				data : {
					emplyrId : emplyrId
				},
				type : 'POST',
				dataType : 'json',
				async : false,
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data) {
					if (data.memberIdChk == 1) {
						document.frm.idChk.value = '0';
						alert("중복된 아이디입니다.");

						idChk = 0;
					}
				}
			});
		}
		
		if(type == 'orgnzt'){
			var businessNum = document.frm.businessNum.value;
			var bisNumChk = 1;
			if (document.frm.act.value == 'write') {
				$.ajax({
					url : '<c:url value="/cms/member/bisNumChk.do"/>',
					data : {
						businessNum : businessNum
					},
					dataType : 'json',
					type : 'POST',
					contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
					success : function(data) {
						if (data.bisNumChk == 1) {
							document.frm.bisNumChk.value = '0';
							alert("중복된 사업자번호입니다.");
	
							bisNumChk = 0;
						} 
					}
				});
			}
		}

		if (idChk) {
			if(type == 'orgnzt'){
				if(bisNumChk){
					document.frm.action = url;
					document.frm.submit();	
				}else{
					alert("중복된 사업자번호입니다.");
				}
			}else {
				document.frm.action = url;
				document.frm.submit();
			}
		}

	}

	function fn_validateId(str) {
		var id = str;
		var spe = id.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		var korean = id.search(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi);

		if ((id.length < 4) || (id.length > 20)) {
			alert("아이디를 4자리 ~ 20자리 이내로 입력해주세요.");
			return false;
		}
		if (id.search(/₩s/) != -1) {
			alert("아이디는 공백없이 입력해주세요.");
			return false;
		}
		if (spe > 0 || korean > 0) {
			alert("아이디는 영문,숫자만 입력해주세요.");
			return false;
		}

		return true;
	}
	
	function fn_validatePW(pw){
		var pattern1 = /[0-9]/;
        var pattern2 = /[a-zA-Z]/;
        var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거
        
        if(!pattern1.test(pw)||!pattern2.test(pw)||!pattern3.test(pw)||pw.length < 9||pw.length > 20){
        	 alert("영문+숫자+특수기호 8자리 이상 20자리 이하로 구성하여야 합니다.");
             return false;
        }
        
        var emplyrId = document.frm.emplyrId.value;
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
	
	function fn_memberIdChk() {
		var id = document.frm.emplyrId.value;

		if (id == "") {
			document.frm.idChk.value = '0';

			alert("아이디를 입력하세요.");
			return false;
		}

		if (!fn_validateId(id)) {
			return false;
		}
		
		$.ajax({
			url : '<c:url value="/cms/member/memberIdChk.do"/>',
			data : {
				emplyrId : id
			},
			dataType : 'json',
			type : 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {
				if (data.memberIdChk == 1) {
					document.frm.idChk.value = '0';
					alert("중복된 아이디입니다.");

					return false;
				} else {
					document.frm.idChk.value = '1';
					alert("사용할 수 있는 아이디입니다.");

					return false;
				}
			}
		});
	}
	
	function fn_bisNumChk() {
		var bisNum = document.frm.businessNum.value;

		if (bisNum == "") {
			document.frm.bisNumChk.value = '0';

			alert("사업자번호를 입력하세요.");
			return false;
		}

		$.ajax({
			url : '<c:url value="/cms/member/bisNumChk.do"/>',
			data : {
				businessNum : bisNum
			},
			dataType : 'json',
			type : 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {
				if (data.bisNumChk == 1) {
					document.frm.bisNumChk.value = '0';
					alert("중복된 사업자번호입니다.");

					return false;
				} else {
					document.frm.bisNumChk.value = '1';
					alert("사용할 수 있는 사업자번호입니다.");

					return false;
				}
			}
		});
	}
	
	function fn_pwInit(){
		var id = document.frm.emplyrId.value;
		
		$.ajax({
			url : '<c:url value="/cms/member/memberPWInit.do"/>',
			data : {
				emplyrId : id
			},
			dataType : 'json',
			type : 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {
				alert("비밀번호 오류 횟수가 초기화 되었습니다.");
				document.frm.action = '<c:url value="/cms/member/memberModify.do"/>';
				document.frm.submit();
			}
		});
	}
	
	function fn_typeChange() {
		var type = document.frm.type.value;

		if (type != '') {
			$("#memberForm").load("<c:url value='/cms/member/memberForm.do'/>", {
				emplyrId : '<c:out value="${result.emplyrId}"/>',
				type : type
			});
		}
	}
	
	function fn_orgnztFlag(th){
		$("#orgnztFlagDetail").attr("readonly", false);
		
		if($(th).val()=='7')
			$("#orgnztFlagDetail").attr("readonly", false);
		else
			$("#orgnztFlagDetail").attr("readonly", true);
	}
	
	function fn_deleteAtChange(){
		if($('input:checkbox[id="switch2"]').is(":checked") == true){
			$("#deleteReason").attr("readonly",false);
		}else{
			$("#deleteReason").attr("readonly",true);
		}

	}
</script>

<!-- Start Content -->
<div class="container-fluid">
	<!-- start page title -->
	<div class="row">
		<div class="col-12">
			<div class="page-title-box">
				<div class="page-title-right">
					<ol class="breadcrumb m-0">
						<li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
						<li class="breadcrumb-item active">회원 관리</li>
					</ol>
				</div>
				<h4 class="page-title">회원 관리</h4>
			</div>
		</div>
	</div>
	<!-- end page title -->

	<form class="needs-validation" novalidate id="frm" name="frm" method="post">
		<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
		<input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>" />
		<input type="hidden" name="searchType" value="<c:out value='${searchVO.searchType}'/>" />
		<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>" />
		<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>" />
		<input type="hidden" name="act" value="write" />
		<input type="hidden" name="idChk" value="0" />
		<input type="hidden" name="bisNumChk" value="0" />

		<div class="row">
			<div class="col-xl-12">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label><i class="fad fa-check"></i>회원구분</label>
									<select class="form-control" required name="type" onchange="javascript:fn_typeChange();">
										<option value="">선택</option>
										<option value="general" <c:if test="${result.type eq 'general' }">selected</c:if>>일반회원</option>
										<option value="orgnzt" <c:if test="${result.type eq 'orgnzt' }">selected</c:if>>기관회원</option>
										<option value="admin" <c:if test="${result.type eq 'admin' }">selected</c:if>>관리자</option>
									</select>
								</div>
							</div>
						</div>
						<div id="memberForm"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
</div>
<!-- /Start Content -->

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />